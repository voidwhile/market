package com.voidwhile.common.jdbc.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.voidwhile.common.dto.ScheduleDTO;
import com.voidwhile.common.jdbc.BaseDAOImpl;
import com.voidwhile.common.jdbc.ScheduleDao;
import com.voidwhile.core.utils.DateFormatUtils;
import com.voidwhile.core.utils.DateUtils;

@Repository
public class ScheduleDaoImpl extends BaseDAOImpl implements ScheduleDao {

	@Override
	public List<ScheduleDTO> countUserSchedule(String userId, Integer year, Integer month) {
		Map<String, Integer> countSummary = countUserSummary(userId, year, month);
		Map<String, Integer> countPlan = countUserPlan(userId, year, month);
		List<ScheduleDTO> rlt = new ArrayList<ScheduleDTO>();
		if (countSummary != null) {
			Set<String> summaryKeys = countSummary.keySet();
			for (String key : summaryKeys) {
				ScheduleDTO dto = new ScheduleDTO();
				dto.setDay(key);
				dto.setSummarynum(countSummary.get(key));
				rlt.add(dto);
			}
		}
		if (countPlan != null) {
			Set<String> planKeys = countPlan.keySet();
			for (String key : planKeys) {
				ScheduleDTO tmp = new ScheduleDTO();
				tmp.setDay(key);
				int index = rlt.indexOf(tmp);
				if (index >= 0) {
					ScheduleDTO t = rlt.get(index);
					t.setPlannum(countPlan.get(key));
				} else {
					tmp.setPlannum(countPlan.get(key));
					rlt.add(tmp);
				}
			}
		}
		return rlt;
	}

	public Map<String, Integer> countUserSummary(String userId, Integer year, Integer month) {
		final Map<String, Integer> rlt = new HashMap<String, Integer>();
		String sql = "SELECT date,count(*) as summarynum  from ("
				+ " select date_format(CONCAT(year,'-',month,'-',day),'%Y-%m-%d') as date from tb_worksummary where type='day' and create_user=? and year=? and month=?) as a"
				+ " group by date";
		this.getJdbcTemplate().query(sql, new Object[] { userId, year, month }, new RowMapper<Map<String, Integer>>() {

			public Map<String, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
				rlt.put(rs.getString("date"), rs.getInt("summarynum"));
				return rlt;
			}
		});
		return rlt;
	}

	public Map<String, Integer> countUserPlan(String userId, Integer year, Integer month) {
		final Map<String, Integer> rlt = new HashMap<String, Integer>();
		String first = DateFormatUtils.format(DateUtils.date(year, month, 1), "yyyy-MM-dd");
		String end = DateFormatUtils.format(DateUtils.date(year, month, DateUtils.getDaysInMonth(year, month)), "yyyy-MM-dd");

		String sql = "SELECT plan_date, count(*) as plannum from ("
				+ "SELECT plan_date from tb_workplan where create_user=? and plan_date>=? and plan_date<=?) a group by plan_date";
		this.getJdbcTemplate().query(sql, new Object[] { userId, first, end }, new RowMapper<Map<String, Integer>>() {

			public Map<String, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
				rlt.put(rs.getString("plan_date"), rs.getInt("plannum"));
				return rlt;
			}
		});

		return rlt;
	}

}
