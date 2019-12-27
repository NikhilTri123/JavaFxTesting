import java.io.FileInputStream;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.geometry.*;

public class JavaFxStuff extends Application
{
   //declarations
   Stage window;

   Scene homeScene;
   Scene blogScene;
   Scene aboutScene;
   Scene contactScene;
   Scene passScene;
   
   int num = 0;
   
   public static void main (String[] args)
   {
      launch(args);
   }
   
   public void start (Stage primaryStage) throws Exception
   {
      window = primaryStage;
      
      GridPane gridPass = new GridPane();
      gridPass.setPadding (new Insets (10, 10, 10, 10));
      gridPass.setVgap(12);
      gridPass.setHgap(0);


      // main screen scene -------------------------------------------------------------------------
      
      // gridpane setup
      GridPane gridMain = new GridPane();
      gridMain.setPadding (new Insets (10, 10, 10, 10));
      gridMain.setVgap(12);
      gridMain.setHgap(0);
      
      // joke button setup
      Button joke = new Button ("Knock knock joke");
      GridPane.setConstraints(joke, 0, 1);
      joke.setOnAction(e ->
      {
         String[] punchline = {"Knock knock", "Who's there?", "Cow says", "Cow says who", "No, cow says moo"};
         System.out.println(punchline[num]);
         num++;
         if (num == 5)
           num = 0;
         
      });
      
      // label nothing setup
      Label nothing = new Label ("I've done nothing here but plan to make it my main \"page\"");
      GridPane.setConstraints(nothing, 0, 2);
      
      // image of dorian setup
      Image dorian = new Image(new FileInputStream("C:\\rsz_dorian.gif"));
      ImageView dorianView = new ImageView(dorian);
      
      // main scene layout setup  
      gridMain.getChildren().addAll(joke, nothing, dorianView);

      BorderPane borderMain = new BorderPane();
      borderMain.setTop(topBar());
      borderMain.setCenter(gridMain);
      
      homeScene = new Scene(borderMain, 1280, 720);
      
      // various scenes
      Label blogLabel = new Label("This is the blog scene");
      BorderPane blogBox = new BorderPane();
      blogBox.setTop(topBar());
      blogBox.setCenter(blogLabel);
      blogScene = new Scene(blogBox, 1280, 720);
      
      Label aboutLabel = new Label("This is the about scene");
      BorderPane aboutBox = new BorderPane();
      aboutBox.setTop(topBar());
      aboutBox.setCenter(aboutLabel);
      aboutScene = new Scene(aboutBox, 1280, 720);
      
      Label contactLabel = new Label("This is the contact scene");
      BorderPane contactBox = new BorderPane();
      contactBox.setTop(topBar());
      contactBox.setCenter(contactLabel);
      contactScene = new Scene(contactBox, 1280, 720);
      
      // stage
      window.setOnCloseRequest(e -> 
      {
         e.consume();
         closeProgram();
      });
      window.setScene(homeScene); // Add the scene to a stage
      window.setTitle("Hello World JavaFx"); // Give the stage a title
      window.show(); // Show the stage

   
      // log in screen scene ---------------------------------------------------------------------------
      
      // log in label setup
      Label getIn = new Label ("Log in here");
      getIn.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
      GridPane.setConstraints(getIn, 0, 0);
      
      // username setup
      String username = "Nikhil";
      TextField usernameInput = new TextField();
      usernameInput.setPromptText("Username");
      usernameInput.setPrefSize(500, 50);
      usernameInput.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
      GridPane.setConstraints(usernameInput, 0, 1);
      
      // password setup
      String password = "Password123";
      TextField passwordInput = new TextField();
      passwordInput.setPromptText("Password");
      passwordInput.setPrefSize(500, 50);
      passwordInput.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
      GridPane.setConstraints(passwordInput, 0, 2);
      
      // wrong password label setup
      Label tryAgain = new Label();
      tryAgain.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14));
      GridPane.setConstraints(tryAgain, 0, 4);
      
      // log in button setup  
      Button logIn = new Button ("Log In");
      logIn.setPrefSize(100, 50);
      logIn.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
      GridPane.setConstraints(logIn, 0, 3);
      logIn.setOnAction(e -> 
      {
         usernameInput.getText();
         passwordInput.getText();
         if (usernameInput.getText().equals(username) && passwordInput.getText().equals(password))
            window.setScene(homeScene);
         else
            tryAgain.setText("Wrong login info, try again");
      });
      
      // enter key setup
      passwordInput.setOnKeyPressed((event) ->
      {
         if ((event).getCode().equals(KeyCode.ENTER))
         {
            if (usernameInput.getText().equals(username) && passwordInput.getText().equals(password))
               window.setScene(homeScene);
            else
               tryAgain.setText("Wrong log in info, try again");       
         }
      });
      
      // temporary switch scene button setup
      Button switching = new Button ("Go to scene 2");
      GridPane.setConstraints(switching, 0, 4);
      switching.setOnAction(e -> window.setScene(homeScene));
      
      // password scene layout setup     
      gridPass.getChildren().addAll(getIn, usernameInput, passwordInput, logIn, tryAgain, switching);
      gridPass.setAlignment(Pos.CENTER);
      gridPass.setHalignment(getIn, HPos.CENTER);
      gridPass.setHalignment(logIn, HPos.CENTER);
      gridPass.setHalignment(tryAgain, HPos.CENTER);
      gridPass.setHalignment(switching, HPos.CENTER);
      BorderPane passBar = new BorderPane();
      passBar.setTop(topBar());
      passBar.setCenter(gridPass);
      
      // scene setup
      passScene = new Scene(passBar, 1280, 720);
   }
   
   
   private void closeProgram ()
   {
      boolean result = AlertBox.display();
      if (result == true)
      {
         System.out.println("Exiting..."); //Example of an action
         window.close();
      }
   }
   
   private StackPane topBar ()
   {
      Rectangle bar = new Rectangle(1280, 52);
      bar.setFill(Color.web("#31BCFF"));
      
      Button homeButton = new Button ("Home");
      homeButton.setPrefSize(100, 50);
      homeButton.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
      homeButton.setStyle("-fx-background-color: #31BCFF;");
      homeButton.setOnAction(e -> window.setScene(homeScene));
      
      Button blogButton = new Button ("Blog");
      blogButton.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
      blogButton.setStyle("-fx-background-color: #31BCFF;");
      blogButton.setPrefSize(100, 50);
      blogButton.setOnAction(e -> window.setScene(blogScene));
      
      Button aboutButton = new Button ("About");
      aboutButton.setPrefSize(100, 50);
      aboutButton.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
      aboutButton.setStyle("-fx-background-color: #31BCFF;");
      aboutButton.setOnAction(e -> window.setScene(aboutScene));
      
      Button contactButton = new Button ("Contact");
      contactButton.setPrefSize(100, 50);
      contactButton.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
      contactButton.setStyle("-fx-background-color: #31BCFF;");
      contactButton.setOnAction(e -> window.setScene(contactScene));
      
      Button loggingIn = new Button ("Log in");
      loggingIn.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
      loggingIn.setStyle("-fx-background-color: #31BCFF;");
      loggingIn.setPrefSize(100, 50);
      loggingIn.setOnAction(e -> window.setScene(passScene));
      
      Pane signingIn = new Pane(); 
      loggingIn.setLayoutX(780);
      loggingIn.setLayoutY(0);
      signingIn.getChildren().add(loggingIn);
      
      HBox buttonsPane = new HBox();     
      buttonsPane.getChildren().addAll(homeButton, blogButton, aboutButton, contactButton, signingIn);
      StackPane menuBar = new StackPane();
      menuBar.getChildren().addAll(bar, buttonsPane);
      return menuBar;
   }
}

class AlertBox
{
   private static boolean answer;
   public static boolean display ()
   {
      Stage window = new Stage();
      
      window.initModality(Modality.APPLICATION_MODAL);
      window.setTitle("Alert!");
      window.setMinWidth(250);
      
      Label label = new Label ("Are you sure you want to close this window?");
      
      Button yes = new Button ("Yes");  
      yes.setOnAction(e -> 
      {
         answer = true;
         window.close();
      });
      
      Button no = new Button ("No");
      no.setOnAction(e -> 
      {
         answer = false;
         window.close();
      });
      
      HBox yesNo = new HBox (10);
      yesNo.setAlignment(Pos.CENTER);
      yesNo.getChildren().addAll(yes, no);
      
      VBox layout = new VBox(10);
      layout.setAlignment(Pos.CENTER);
      layout.getChildren().addAll(label, yesNo);
      
      Scene scene = new Scene(layout);
      window.setScene(scene);
      window.showAndWait();
      
      return answer;
   }
}          