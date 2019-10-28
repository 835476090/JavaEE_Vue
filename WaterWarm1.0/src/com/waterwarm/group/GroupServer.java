package com.waterwarm.group;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.waterwarm.sql.SQLHandler;

public class GroupServer
{
	private SQLHandler sql;
	private ResultSet rs;
	public GroupServer()
	{
		sql= new SQLHandler();
	}
	public boolean add(JSONObject jo)
	{
		try
		{
			String sqlstr="insert into group values("
			+jo.getInt("groupid")+",'"
			+jo.getString("groupname")+"');";
			boolean b=sql.statement.execute(sqlstr);
			return b;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} catch (JSONException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean delete(int gcid)
	{
		String sqlstr=" delete from group where groupid="
		+gcid+";";
		try
		{
			boolean b=sql.statement.execute(sqlstr);
			return b;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(int gcid,JSONObject jo)
	{
		try
		{
			String sqlstr="update group set "
			+"groupid="+jo.getInt("groupid")
			+" groupname='"+jo.getString("groupname")
			+ "' where groupid="+gcid;
			boolean b=sql.statement.execute(sqlstr);
			return b;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} catch (JSONException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public JSONObject select(int gcid)
	{
		String sqlstr="select * from group where groupid="
		+gcid+";";
		JSONObject jObject=new JSONObject();
		try
		{
			rs=sql.statement.executeQuery(sqlstr);
			while(rs.next())
			{
				int groupid=rs.getInt(1);
				String groupname = rs.getString(2);
				System.out.println("groupid："+groupid+" groupname："+groupname);
				jObject.put("groupid", groupid)
						.put("groupname", groupname);
			}
			return jObject;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} catch (JSONException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public JSONArray selectALL()
	{
		String sqlstr="select * from goodsgroup;";
		JSONArray ja=new JSONArray();
		try
		{
			rs=sql.statement.executeQuery(sqlstr);
			while(rs.next())
			{
				int groupid=rs.getInt(1);
				String groupname=rs.getString(2);

				System.out.println("groupid："+groupid+" groupname："+groupname);
				JSONObject jo=new JSONObject()
						.put("groupid", groupid)
						.put("groupname", groupname);
				ja.put(jo);
			}
			return ja;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return new JSONArray();
		} catch (JSONException e)
		{
			e.printStackTrace();
			return new JSONArray();
		}
	}
	public void close()
	{
		try
		{
			if (rs!=null)
			{
				rs.close();
			}
			sql.statement.close();
			sql.conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}
