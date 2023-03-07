INSERT INTO advertising (id, user_id, text, email, priority, address, is_active, is_favorite) VALUES (1, 1, 'test text', 'test@test.com', 1, 'test city', true, false)
INSERT INTO advertising (id, user_id, text, email, priority, address, is_active, is_favorite) VALUES (2, 1, 'test text2', 'test2@test.com', 2, 'test city2', true, false)

INSERT INTO rating (id, user_id, rating_value) VALUES (1, 1, 3)
INSERT INTO rating (id, user_id, rating_value) VALUES (2, 2, 4)
INSERT INTO rating (id, user_id, rating_value) VALUES (3, 1, 5)