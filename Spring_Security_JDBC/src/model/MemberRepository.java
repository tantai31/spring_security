package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import util.Helper;

public class MemberRepository {
	private static JdbcTemplate jdbc = CSDL.getJdbc();
	public int add(Member obj) {
		obj.setId(Helper.randomLong());
		int a = jdbc.update("INSERT INTO `Member`(`MemberId`,`Username`,`Password`,`Email`,`Gender`,`Tel`,`Address`) VALUES(?,?,?,?,?,?,?)",obj.getId(),obj.getUserName(),Helper.bcrypt(obj.getPassWord()),obj.getEmail(),obj.isGender(),obj.getTel(),obj.getAddress());
		int b = jdbc.update("INSERT INTO `MemberInRole`(`RoleId`,`MemberId`) VALUES(?,?)",1,obj.getId());
		return a+b;
	}
	public Member logon(String username) {
		Member member = jdbc.queryForObject("SELECT `MemberId`, `Username`, `Password` FROM `Member` WHERE `Username` = ?", new RowMapper<Member>(){
			@Override
			public Member mapRow(ResultSet rs, int numRow) throws SQLException {
				return new Member(rs.getLong("MemberId"), rs.getString("UserName"), rs.getString("Password"));
			}
		}, username);
		member.setRoles(getRolesByMemberId(member.getId()));
		return member;
	}
	public List<Role> getRolesByMemberId(long id) {
		return jdbc.query("SELECT Role.RoleId, RoleName FROM Role INNER JOIN MemberInRole ON Role.RoleId = MemberInRole.RoleId WHERE MemberId = ?", new RowMapper<Role>() {

			@Override
			public Role mapRow(ResultSet rs, int arg1) throws SQLException {
				Role rol = new Role();
				rol.setId(rs.getInt("roleid"));
				rol.setName(rs.getString("roleName"));
				return rol;
			}
		}, id);
	}
	
}
