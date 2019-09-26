package rpc;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;


public class ItemHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
              * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
              */
               protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	 DBConnection connection = DBConnectionFactory.getConnection();
  	 try {
  		 JSONObject input = RpcHelper.readJSONObject(request);
  		 String userId = input.getString("user_id");
  		 JSONArray array = input.getJSONArray("favorite");
  		 String itemId = array.getString(0);
  		 connection.setFavoriteItems(userId, itemid);
  		 RpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
  		
  	 } catch (Exception e) {
  		 e.printStackTrace();
  	 } finally {
  		 connection.close();
  	 }
  	
   }

             /**
              * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
             */
             protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	 DBConnection connection = DBConnectionFactory.getConnection();
  	 try {
  		 JSONObject input = RpcHelper.readJSONObject(request);
  		 String userId = input.getString("user_id");
  		 JSONArray array = input.getJSONArray("favorite");
  		 String itemId = array.getString(0);
  		
  		 connection.unsetFavoriteItems(userId, itemId);
  		 RpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
  		
  	 } catch (Exception e) {
  		 e.printStackTrace();
  	 } finally {
  		 connection.close();
  	 }
   }
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// default line
		//response.getWriter().append("Served at: ").append(request.getContextPath());
			String userId = request.getParameter("user_id");
		JSONArray array = new JSONArray();
		
		DBConnection conn = DBConnectionFactory.getConnection();
		try {
			Set<Item> items = conn.getFavoriteItems(userId);
			for (Item item : items) {
				JSONObject obj = item.toJSONObject();
				obj.put("favorite", true);
				array.put(obj);
			}
			
			RpcHelper.writeJsonArray(response, array);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}		
		// use close to close the writer
		// this can set free some resource and it is a good habit
		writer.close();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


