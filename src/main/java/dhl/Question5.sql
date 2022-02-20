-----------------------SCHEMA--------------------------------
CREATE TABLE candidates
(
    id     int PRIMARY KEY,
    gender text,
    age    int,
    party  TEXT
);

INSERT INTO candidates (id, gender, age, party)
VALUES (1, 'M', 50, 'A'),
       (2, 'M', 50, 'B'),
       (3, 'F', 50, 'A'),
       (4, 'M', 50, 'B'),
       (5, 'F', 50, 'A'),
       (6, 'M', 50, 'B');

CREATE TABLE results
(
    constituency_id int,
    candidate_id    int,
    votes           int,
    PRIMARY KEY (constituency_id, candidate_id)
);

INSERT INTO results (constituency_id, candidate_id, votes)
VALUES (1, 1, 10),
       (1, 2, 5),
       (1, 3, 10),
       (1, 4, 5),
       (1, 5, 10),
       (1, 6, 5),
       (2, 1, 5),
       (2, 2, 10),
       (2, 3, 5),
       (2, 4, 10),
       (2, 5, 5),
       (2, 6, 10);

-----------------------SQL QUERY--------------------------------
select party,
       constituency_id
from (
         select party,
                constituency_id,
                sum(votes) as total_votes,
                dense_rank()  over (partition by party order by sum(votes) desc) as rnk
         from candidates qc
                  join results qr
                       on qc.id = qr.candidate_id
         group by party,
                  constituency_id
     ) val
where rnk = 1