-- mainorders テーブルの初期データ
INSERT INTO mainorders (store_name, person_in_charge, order_date, remarks) VALUES ('店舗2', '担当者B', '2025-06-20', '備考B');
INSERT INTO mainorders (store_name, person_in_charge, order_date, remarks) VALUES ('店舗3', '担当者C', '2025-06-22', '備考C');

-- order_details テーブルの初期データ (店舗2)
INSERT INTO order_details (order_id, item_name, quantity) SELECT order_id, '麺A', 10 FROM mainorders WHERE store_name = '店舗2' AND person_in_charge = '担当者B' ORDER BY order_id DESC LIMIT 1;
INSERT INTO order_details (order_id, item_name, quantity) SELECT order_id, 'チャーシュー', 5 FROM mainorders WHERE store_name = '店舗2' AND person_in_charge = '担当者B' ORDER BY order_id DESC LIMIT 1;

-- order_details テーブルの初期データ (店舗3)
INSERT INTO order_details (order_id, item_name, quantity) SELECT order_id, '麺B', 20 FROM mainorders WHERE store_name = '店舗3' AND person_in_charge = '担当者C' ORDER BY order_id DESC LIMIT 1;
INSERT INTO order_details (order_id, item_name, quantity) SELECT order_id, '麺C', 15 FROM mainorders WHERE store_name = '店舗3' AND person_in_charge = '担当者C' ORDER BY order_id DESC LIMIT 1; 