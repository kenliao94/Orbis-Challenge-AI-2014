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
  

  //Implement a function that turns in a player's first person perspective

   public PlayerAction turning (String turn, LightCycle playerCycle){
    // The turn String is either left, right or forward
    // It turns based on the current direction of the playerCycle
    Direction curr_direction = playerCycle.getDirection();

    if (turn == "right"){
      //player wants to turn right in first person perspetive
      

      if(curr_direction.equals(Direction.UP)){
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_RIGHT : PlayerAction.MOVE_RIGHT;
      }

      else if(curr_direction.equals(Direction.RIGHT)){
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_DOWN : PlayerAction.MOVE_DOWN;
      }

      else if(curr_direction.equals(Direction.DOWN)){
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_LEFT : PlayerAction.MOVE_LEFT;
      }

      else{
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_UP : PlayerAction.MOVE_UP;
      }
    }

    else if (turn == "left") {
      //player wants to turn left in first person perspective 
      if (curr_direction.equals(Direction.UP)) {
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_LEFT : PlayerAction.MOVE_LEFT;
      }

      else if(curr_direction.equals(Direction.RIGHT)){
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_UP : PlayerAction.MOVE_UP;
      }

      else if(curr_direction.equals(Direction.DOWN)){
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_RIGHT : PlayerAction.MOVE_RIGHT;
      }

      else{
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_DOWN : PlayerAction.MOVE_DOWN;
      }

    }

    else {
      //player wants to keep going forward in first person pespective
      if (curr_direction.equals(Direction.UP)) {
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_UP : PlayerAction.MOVE_UP;
      }

      else if (curr_direction.equals(Direction.RIGHT)) {
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_RIGHT : PlayerAction.MOVE_RIGHT;
      }

      else if (curr_direction.equals(Direction.DOWN)){
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_DOWN : PlayerAction.MOVE_DOWN;
      }

      else{
        return playerCycle.hasPowerup()? PlayerAction.ACTIVATE_POWERUP_MOVE_LEFT : PlayerAction.MOVE_LEFT;
      }
    }
    
   }


   public Point check_point (LightCycle playerCycle, String view_direction){
      //This function check the point in first person perspective 
      
      // get current direction, current location and initialize a new Point object 
      Direction curr_direction = playerCycle.getDirection();

      Point curr_point = playerCycle.getPosition();

      int curr_x_pos = curr_point.x;
      int curr_y_pos = curr_point.y;

      Point new_point = new Point();

      if(view_direction == "forward"){
      // If the person is checking the point in front of it
        if (curr_direction.equals(Direction.UP)) {
          // if curr_direction is UP
          new_point.setLocation(curr_x_pos,curr_y_pos - 1);
        }

        else if (curr_direction.equals(Direction.RIGHT)) {
          // if curr_direction is RIGHT
          new_point.setLocation(curr_x_pos + 1, curr_y_pos);
        }


        else if (curr_direction.equals(Direction.DOWN)) {
          // if curr_direction is DOWN
          new_point.setLocation(curr_x_pos, curr_y_pos + 1);
        }

        else{
          // if curr_direction is LEFT
          new_point.setLocation(curr_x_pos - 1, curr_y_pos);
        }
      }

      else if (view_direction == "right") {
      // If the person is checking the point to the right
           if (curr_direction.equals(Direction.UP)) {
          // if curr_direction is UP
            new_point.setLocation(curr_x_pos + 1, curr_y_pos);
        }

        else if (curr_direction.equals(Direction.RIGHT)) {
          // if curr_direction is RIGHT
            new_point.setLocation(curr_x_pos, curr_y_pos + 1);
        }


        else if (curr_direction.equals(Direction.DOWN)) {
          // if curr_direction is DOWN
            new_point.setLocation(curr_x_pos - 1, curr_y_pos);
        }

        else{
          // if curr_direction is LEFT
            new_point.setLocation(curr_x_pos, curr_y_pos - 1);
        }

      }


      else{
      // If the person is checking the point to the left

           if (curr_direction.equals(Direction.UP)) {
          // if curr_direction is UP
            new_point.setLocation(curr_x_pos - 1, curr_y_pos);
        }

        else if (curr_direction.equals(Direction.RIGHT)) {
          // if curr_direction is RIGHT
          new_point.setLocation(curr_x_pos, curr_y_pos - 1);
        }


        else if (curr_direction.equals(Direction.DOWN)) {
          // if curr_direction is DOWN
          new_point.setLocation(curr_x_pos + 1, curr_y_pos);
        }

        else{
          // if curr_direction is LEFT
          new_point.setLocation(curr_x_pos, curr_y_pos + 1);
        }
      }


    return new_point;
   }





   //Implement keep right algorithm



  @Override
  public PlayerAction getMove(TronGameBoard map,
      LightCycle playerCycle, LightCycle opponentCycle, int moveNumber) {
    /*
       The keep right algorithm is to keep as right as possible, so

       Right > Forward > Left

    */

    //Get the position of the player
    Point curr_pos = playerCycle.getPosition();
    int curr_x_pos = curr_pos.x;
    int curr_y_pos = curr_pos.y;
    Direction curr_direction = playerCycle.getDirection();

    //Initiate check point
    Point view_point;

    //If right check point is empty or has power up, then move right
    if(map.isOccupied((view_point = check_point(playerCycle,"right")).x,(view_point = check_point(playerCycle,"right")).y) == false || map.tileType((view_point = check_point(playerCycle,"right")).x,(view_point = check_point(playerCycle,"right")).y).equals(TileTypeEnum.POWERUP)){

      return turning("right", playerCycle);
    }
   
    //else if forward check point is empty or has power up, then move forward
    else if(map.isOccupied((view_point = check_point(playerCycle,"forward")).x,(view_point = check_point(playerCycle,"forward")).y) == false || map.tileType((view_point = check_point(playerCycle,"forward")).x,(view_point = check_point(playerCycle,"forward")).y).equals(TileTypeEnum.POWERUP)){
      return turning("forward", playerCycle);
    }

    //else if left check point is empty or has power up, then move left
    else{
      return turning("left", playerCycle);
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