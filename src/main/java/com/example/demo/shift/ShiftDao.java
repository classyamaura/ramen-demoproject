package com.example.demo.shift;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShiftDao{

	private final JdbcTemplate db;

	public ShiftDao(JdbcTemplate db) {
		this.db = db;
	}
	
	
	//一覧表示
	public List<ShiftEntity> searchDb() {
		String sql = "SELECT * FROM matchapp";
		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> Db = db.queryForList(sql);
		//画面に表示しやすい形のList(resultDB2)を用意
		List<ShiftEntity> resultDb = new ArrayList<ShiftEntity>();
		//1件ずつピックアップ
		for (Map<String, Object> result : Db) {
			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			ShiftEntity entshiftdb = new ShiftEntity();
			//id、nameのデータをentformdbに移す
			entshiftdb.setId((int) result.get("id"));
			entshiftdb.setName((String) result.get("name"));
			entshiftdb.setDate((String) result.get("date"));
			entshiftdb.setJikan((String) result.get("jikan"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb.add(entshiftdb);
		}
		//Controllerに渡す
		return resultDb;
	}
}
