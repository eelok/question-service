INSERT INTO question(id, question_text)
VALUES
(101,'how many legs does a spider has?'),
(102,'what is the longest river on earth?'),
(103, 'how many colors does the rainbow have?');

INSERT INTO answer(id, question_id, answer_text, is_correct)
VALUES
(201, 101, 8, true),
(202, 101, 16, false),
(203, 101, 6, false),
(204, 101, 12, false),

(205, 102, 'Kander', false),
(206, 102, 'Nil', true),
(207, 102, 'Spree', false),
(208, 102, 'Siena', false),

(209, 103, 33, false),
(210, 103, 5, false),
(211, 103, 8, false),
(212, 103, 7, true);

