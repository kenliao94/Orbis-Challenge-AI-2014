import java.util.Random;
import java.awt.Point;
import java.lang.Math;
import com.orbischallenge.tron.api.PlayerAction;
import com.orbischallenge.tron.client.api.LightCycle;
import com.orbischallenge.tron.client.api.TronGameBoard;
import com.orbischallenge.tron.client.api.TileTypeEnum;
import com.orbischallenge.tron.protocol.TronProtocol;
import com.orbischallenge.tron.protocol.TronProtocol.PowerUpType;
import com.orbischallenge.tron.protocol.TronProtocol.Direction;


public class PlayerAI implements Player {
	
	private Random randomMovePicker;
	private int randMove;

	@Override
	public void newGame(TronGameBoard map,  
			LightCycle playerCycle, LightCycle opponentCycle) {
		/*
		randomMovePicker = new Random();
		*/

    int map_edge = map.length() - 1;   


    return;
		
	}
	

  //Implement a vision function
  //This function give you the percentage of occupacy within the vision
  //It will take TOP Bottom RIGHT LEFT as vision direction, create a 3x3 square vision, output percentage as double






	@Override
	public PlayerAction getMove(TronGameBoard map,
			LightCycle playerCycle, LightCycle opponentCycle, int moveNumber) {
		/*
	
    */

    //Get the position of the player
    Point curr_pos = playerCycle.getPosition();
    int curr_x_pos = curr_pos.x;
    int curr_y_pos = curr_pos.y;

    String closest_wall;
    
    //Determine which wall it is close to
    
    if (curr_x_pos < curr_y_pos){
      // It is either Left or Bottom
      if (curr_y_pos < -1 * curr_x_pos + map.length() - 1){
        closest_wall = "LEFT";
      }
      else{
        closest_wall = "Bottom";
      }

    } 

    else{
      // It is either Right or Top
      if (curr_y_pos < -1 * curr_x_pos + map.length() - 1){
        closest_wall = "TOP";
      }
      else{
        closest_wall = "RIGHT";
      }
    }  


    //Check if the zone is actually correct 


    //implement the moving algorithm

    //Close to the top wall assign TOP
    if(closest_wall == "TOP"){
      if (map.isOccupied(curr_x_pos,curr_y_pos-1) == false || map.tileType(curr_x_pos,curr_y_pos-1).equals(TileTypeEnum.POWERUP)){
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_UP : PlayerAction.MOVE_UP;
      }
      else if (map.isOccupied(curr_x_pos - 1, curr_y_pos) == false || map.tileType(curr_x_pos - 1, curr_y_pos).equals(TileTypeEnum.POWERUP)) {
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_LEFT : PlayerAction.MOVE_LEFT;
      }
      else if (map.isOccupied(curr_x_pos, curr_y_pos + 1) == false || map.tileType(curr_x_pos, curr_y_pos + 1).equals(TileTypeEnum.POWERUP)){
        
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_DOWN : PlayerAction.MOVE_DOWN;
      }
      else {
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_RIGHT : PlayerAction.MOVE_RIGHT;
      }
    }
    //Close to the left wall assign LEFT
    else if (closest_wall == "LEFT"){
      if (map.isOccupied(curr_x_pos-1,curr_y_pos) == false || map.tileType(curr_x_pos-1,curr_y_pos).equals(TileTypeEnum.POWERUP)){
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_LEFT : PlayerAction.MOVE_LEFT;
      }
      else if (map.isOccupied(curr_x_pos,curr_y_pos + 1) == false || map.tileType(curr_x_pos,curr_y_pos + 1).equals(TileTypeEnum.POWERUP)){
        
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_DOWN : PlayerAction.MOVE_DOWN;
      }
      else if (map.isOccupied(curr_x_pos + 1,curr_y_pos) == false || map.tileType(curr_x_pos + 1,curr_y_pos).equals(TileTypeEnum.POWERUP)) {
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_RIGHT : PlayerAction.MOVE_RIGHT;
      }
      else{
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_UP : PlayerAction.MOVE_UP;
      }
    }
    //Close to the right wall assign RIGHT
    else if (closest_wall == "RIGHT"){
      if (map.isOccupied(curr_x_pos + 1, curr_y_pos) == false || map.tileType(curr_x_pos + 1, curr_y_pos).equals(TileTypeEnum.POWERUP)) {
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_RIGHT : PlayerAction.MOVE_RIGHT;
      }
      else if (map.isOccupied(curr_x_pos,curr_y_pos -1 ) == false || map.tileType(curr_x_pos,curr_y_pos -1).equals(TileTypeEnum.POWERUP)) {
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_UP : PlayerAction.MOVE_UP;
      }
      else if(map.isOccupied(curr_x_pos-1,curr_y_pos) == false || map.tileType(curr_x_pos-1,curr_y_pos).equals(TileTypeEnum.POWERUP)){
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_LEFT : PlayerAction.MOVE_LEFT;
      }
      else{
        
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_DOWN : PlayerAction.MOVE_DOWN;
      }
    }
    //Close to the bottom wall assign BOTTOM
    else{
      if (map.isOccupied(curr_x_pos,curr_y_pos + 1) == false || map.tileType(curr_x_pos,curr_y_pos + 1).equals(TileTypeEnum.POWERUP)) {
        
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_DOWN : PlayerAction.MOVE_DOWN;
      }
      else if (map.isOccupied(curr_x_pos + 1,curr_y_pos) == false || map.tileType(curr_x_pos + 1,curr_y_pos).equals(TileTypeEnum.POWERUP)) {
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_RIGHT : PlayerAction.MOVE_RIGHT;        
      }
      else if (map.isOccupied(curr_x_pos, curr_y_pos - 1) == false || map.tileType(curr_x_pos, curr_y_pos - 1).equals(TileTypeEnum.POWERUP)) {
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_UP : PlayerAction.MOVE_UP;
      }
      else{
        
return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_LEFT : PlayerAction.MOVE_LEFT;
      }
    }
  
    
	}

}

/**

8888888 8888888888 8 888888888o.      ,o888888o.     b.             8 
      8 8888       8 8888    `88.  . 8888     `88.   888o.          8 
      8 8888       8 8888     `88 ,8 8888       `8b  Y88888o.       8 
      8 8888       8 8888     ,88 88 8888        `8b .`Y888888o.    8 
      8 8888       8 8888.   ,88' 88 8888         88 8o. `Y888888o. 8 
      8 8888       8 888888888P'  88 8888         88 8`Y8o. `Y88888o8 
      8 8888       8 8888`8b      88 8888        ,8P 8   `Y8o. `Y8888 
      8 8888       8 8888 `8b.    `8 8888       ,8P  8      `Y8o. `Y8 
      8 8888       8 8888   `8b.   ` 8888     ,88'   8         `Y8o.` 
      8 8888       8 8888     `88.    `8888888P'     8            `Yo
      
                                Quick Guide
                --------------------------------------------

        1. THIS IS THE ONLY .JAVA FILE YOU SHOULD EDIT THAT CAME FROM THE ZIPPED STARTER KIT
        
        2. Any external files should be accessible from this directory

        3. newGame is called once at the start of the game if you wish to initialize any values
       
        4. getMove is called for each turn the game goes on

        5. map represents the game field. map.isOccupied(2, 2) returns whether or not something is at position (2, 2)
        								  map.tileType(2, 2) will tell you what is at (2, 2). A TileTypeEnum is returned.
        
        6. playerCycle is your lightcycle and is what the turn you respond with will be applied to.
                playerCycle.getPosition() is a Point object representing the (x, y) position
                playerCycle.getDirection() is the direction you are travelling in. can be compared with Direction.DIR where DIR is one of UP, RIGHT, DOWN, or LEFT
                playerCycle.hasPowerup() is a boolean representing whether or not you have a powerup
                playerCycle.isInvincible() is a boolean representing whether or not you are invincible
                playerCycle.getPowerupType() is what, if any, powerup you have
        
        7. opponentCycle is your opponent's lightcycle.

        8. You ultimately are required to return one of the following:
                                                PlayerAction.SAME_DIRECTION
                                                PlayerAction.MOVE_UP
                                                PlayerAction.MOVE_DOWN
                                                PlayerAction.MOVE_LEFT
                                                PlayerAction.MOVE_RIGHT
                                                PlayerAction.ACTIVATE_POWERUP
                                                PlayerAction.ACTIVATE_POWERUP_MOVE_UP
                                                PlayerAction.ACTIVATE_POWERUP_MOVE_DOWN
                                                PlayerAction.ACTIVATE_POWERUP_MOVE_LEFT
                                                PlayerAction.ACTIVATE_POWERUP_MOVE_RIGHT
      	
     
        9. If you have any questions, contact challenge@orbis.com
        
        10. Good luck! Submissions are due Sunday, September 21 at noon. 
            You can submit multiple times and your most recent submission will be the one graded.
 */