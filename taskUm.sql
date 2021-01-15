CREATE SCHEMA TEMP;

CREATE TABLE TEMP.events (
	event_type integer NOT NULL,
	value integer NOT NULL,
	time timestamp NOT NULL,
	unique(event_type, time)	
);

insert into TEMP.events (event_type, value, time) values(2, 5, '2015-05-09 12:42:00');
insert into TEMP.events (event_type, value, time) values(4, -42, '2015-05-09 13:19:57');
insert into TEMP.events (event_type, value, time) values(2, 2, '2015-05-09 14:48:30');
insert into TEMP.events (event_type, value, time) values(2, 7, '2015-05-09 12:54:39');
insert into TEMP.events (event_type, value, time) values(3, 16, '2015-05-09 13:19:57');
insert into TEMP.events (event_type, value, time) values(3, 20, '2015-05-09 15:01:09');



SELECT tb.event_type, epenultimate.value - eold.value AS value
FROM (
	SELECT e.event_type,
	       min(e.time) AS oldest,
	       (
		       SELECT min(time)
				 FROM TEMP.events e1
				WHERE e1.event_type = e.event_type 
				  AND e1.time > min(e.time)
				GROUP BY event_type
		   ) AS penultimate	
	 FROM TEMP.events e 
	 GROUP BY e.event_type
	 HAVING count(e.time) > 1
) tb 
JOIN TEMP.events eold ON eold.event_type = tb.event_type AND eold.time = tb.oldest
JOIN TEMP.events epenultimate ON epenultimate.event_type = tb.event_type AND epenultimate.time = tb.penultimate
ORDER BY tb.event_type
