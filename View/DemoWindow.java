// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package View;

import Behaviour.GameObject;
import Controller.Controller;
import Drawing.Sprite;
import Drawing.TextureManager;
import Events.*;
import Events.GameEvents.*;
import Logic.Game.RegularGameLogic;
import Logic.GameLogic;
import Logic.Movement.RegularMovement;
import Model.CodeManager;
import Model.Game;
import Model.Enemy.EnemyManager;
import Model.Levels.InstructionsLevel;
import Model.Levels.Level;
import Model.Mathematical.Vertex;
import Model.Obj.ObjLoader.ObjectManager;
import Environment.Sound.SoundManager;
import Objects.Player;
import javafx.scene.input.KeyCode;

import javax.media.opengl.GL;

public class DemoWindow extends Window implements Listener {

    private Controller controller;
    private View view;
    private Game game;

    public DemoWindow(Controller controller) {
        this.controller = controller;
        EventManager.getInstance().addListener(EventType.AddSprite, this);
        EventManager.getInstance().addListener(EventType.RemoveSprite, this);
        EventManager.getInstance().addListener(EventType.AddObject, this);
        EventManager.getInstance().addListener(EventType.RemoveObject, this);
        EventManager.getInstance().addListener(EventType.KeyEvent, this);
        EventManager.getInstance().addListener(EventType.StartGame, this);
        EventManager.getInstance().addListener(EventType.EndGame, this);
        EventManager.getInstance().addListener(EventType.NextLevel, this);
    }

    public void demoWindow() {
        Level instructionLevel = new InstructionsLevel();
        Player player = new Player(new RegularMovement(), 0,0,0, 100);
        Vertex start = instructionLevel.getPlayerStartingPosition();
        player.getSystem().setPositionX(start.getX());
        player.getSystem().setPositionY(start.getY());
        player.getSystem().setPositionZ(start.getZ());
        view = new View(player, 0);
        // initialize the view
        view.initialize(instructionLevel.maxDistanceToDraw());
        // add a key listener to the view
        view.addKeyListener(new KeyEventHandler());

        GameLogic logic = new RegularGameLogic();
        game = new Game(logic, player);
        // initialize the game (model)
        game.initialize();

        instructionLevel.initialize(player);

        view.setAreas(instructionLevel.getAreas());
        game.setAreas(instructionLevel.getAreas());
        instructionLevel.clearObjects();
    }

    @Override
    public void notify(GameEvent event) {
        // check if the event is a specific event
        if (event.isType() == EventType.AddSprite) {
            Sprite sprite = ((SpriteEvent) event).getSprite();
            view.addSprite(sprite);
        } else if (event.isType() == EventType.RemoveSprite) {
            Sprite sprite = ((SpriteEvent) event).getSprite();
            view.removeSprite(sprite);
        } else if (event.isType() == EventType.AddObject) {
            GameObject object = ((ObjectEvent) event).getObject();
            object.addToGame(game);
        } else if (event.isType() == EventType.RemoveObject) {
            GameObject object = ((ObjectEvent) event).getObject();
            object.removeFromGame(game);
        } else if(event.isType() == EventType.KeyEvent) {
            // extract event information
            KeyCode code = ((KeyEvent) event).getKey();

            if(code == KeyCode.ESCAPE) {
                backToMain();
            }
        }
    }

    private void backToMain() {
        EventManager.getInstance().removeListener(this);
        GL gl = view.getGlu();
        if(gl != null) {
            TextureManager.getInstance().clear(gl.getGL2());
        }
        view.stop();
        game.stop();
        ObjectManager.getInstance().empty();
        EnemyManager.getInstance().empty();
        SoundManager.getInstance().stopAll();
        CodeManager.getInstance().reset();
        controller.startingWindow();
    }
}
