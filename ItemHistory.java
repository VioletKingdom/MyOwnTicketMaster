package rpc;



public class ItemHistory extends HttpServlet {

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


}
