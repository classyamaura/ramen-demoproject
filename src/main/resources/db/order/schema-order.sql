-- 発注関連テーブルのスキーマ定義用ファイル
SELECT 1;
-- TODO:

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    weight INT
);