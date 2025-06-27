-- mainorders テーブルの初期データ
INSERT INTO mainorders (store_name, person_in_charge, order_date, remarks) VALUES ('市川店', '担当者B', '2025-06-20', '');
INSERT INTO mainorders (store_name, person_in_charge, order_date, remarks) VALUES ('松戸店', '担当者C', '2025-06-22', '');

-- order_details テーブルの初期データ (店舗2)
INSERT INTO order_details (order_id, item_name, quantity) SELECT order_id, '麺A', 10 FROM mainorders WHERE store_name = '市川店' AND person_in_charge = '担当者B' ORDER BY order_id DESC LIMIT 1;
INSERT INTO order_details (order_id, item_name, quantity) SELECT order_id, 'チャーシュー', 5 FROM mainorders WHERE store_name = '市川店' AND person_in_charge = '担当者B' ORDER BY order_id DESC LIMIT 1;

-- order_details テーブルの初期データ (店舗3)
INSERT INTO order_details (order_id, item_name, quantity) SELECT order_id, '麺B', 10 FROM mainorders WHERE store_name = '松戸店' AND person_in_charge = '担当者C' ORDER BY order_id DESC LIMIT 1;
INSERT INTO order_details (order_id, item_name, quantity) SELECT order_id, '麺C', 15 FROM mainorders WHERE store_name = '松戸店' AND person_in_charge = '担当者C' ORDER BY order_id DESC LIMIT 1; 