package memorygamereference;

/**********************************************************************
 * Vic's version 10.0
 * September 1, 2010
 **********************************************************************/
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main
{
    Random randomNumberGenerator;
    public PlayAction gamePlay;
    private JFrame mainWindow; // GUI related instance variables
    private JButton[] buttons;
    private ImageIcon coverIcon; // Reset button values
    private ArrayList<Integer> twentyCardList;
    private ArrayList<ImageIcon> iconList;
    private boolean doop;

    public static void main(String[] args)
    {
        new Main().setUpGame();
    }

    private void setUpGame()
    {
        /**************************************************************
         * Instantiate classes, set up windows and panels.
         **************************************************************/
        twentyCardList = new ArrayList<Integer>();
        randomNumberGenerator = new Random();
        gamePlay = new PlayAction();
        iconList = new ArrayList<ImageIcon>();
        coverIcon = new ImageIcon("/Users/george/NetBeansProjects/ShrutisMemoryGame/src/mysimplememorygame/images/CardCover.jpg");
        mainWindow = new JFrame("Simple Memory Game");
        buttons = new JButton[20];
        gamePlay.setTwentyCardList(twentyCardList);
        gamePlay.setMainWindow(mainWindow);

        for (int i = 0; i < 52; i++) //Fill ArrayList with imageIcons.
        {
            ImageIcon cardIcon = new ImageIcon("/Users/george/NetBeansProjects/ShrutisMemoryGame/src/mysimplememorygame/images/Card" + i + ".jpg");
            iconList.add(cardIcon);//Make imageIcons out of the card images and fill array.
        }
        mainWindow.setSize(500, 600);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(500, 500);
        mainWindow.setBackground(Color.DARK_GRAY);
        mainWindow.setLayout(new GridLayout(4, 5, 5, 5)); // Create a layout with rows = 4, 5 pixel gaps.

        /**********************************************************************************
         * Make an array of buttons and add them to the JFrame; add button listeners
         **********************************************************************************/
        for (int i = 0; i < 20; i++)  // Create the array of 20 buttons; add to JFrame.
        {
            buttons[i] = new JButton(coverIcon);  // Create and add buttons to the game panel with cover image.
            buttons[i].setMnemonic(i); //Add ident number to button
            buttons[i].addActionListener(gamePlay); // Add The Action Listener To The Buttons
            buttons[i].setVisible(true);
            mainWindow.add(buttons[i]);
        }
        mainWindow.setVisible(true);

        /**********************************************************************************
         * Fill the twentyCardList array with 10 random numbers in the top half
         **********************************************************************************/
        for (int i = 0; i < 10; i++)
        {
            twentyCardList.add(randomNumberGenerator.nextInt(52));
        }

        removeDuplicatesFromTopHalf();

        /***********************************************************************************************************
         * Fill the bottom half of the twentyCardList array with 10 random numbers copied at random from the  top half
         *************************************************************************************************************/
        for (int i = 0; i < 10; i++)
        {
            twentyCardList.add(twentyCardList.get(randomNumberGenerator.nextInt(10)));
        }
        removeDuplicatesFromBottomHalf();
    }

    public void removeDuplicatesFromTopHalf()
    {
        int pass = 0;
        doop = true;
        while (doop == true) // Not the big boys way.
        {
            doop = false;
            for (int pointer1 = 0; pointer1 < 10; pointer1++)
            {
                for (int pointer2 = 0; pointer2 < 10; pointer2++)
                {
                    if (pointer1 == pointer2)// i & j refer to same entry so do nothing
                    {
                        //Do nothing,both pointers are pointing to the same item in twentyCardList.
                    } else if (twentyCardList.get(pointer1).equals(twentyCardList.get(pointer2)))
                    {
                        doop = true;
                        twentyCardList.set(pointer1, randomNumberGenerator.nextInt(52)); // Add another random number to twentyCardList.
                    }
                }
            }
        }
    }

    public void removeDuplicatesFromBottomHalf()
    {
        int pass = 0;
        doop = true;
        while (doop) // The big boys way.
        {
            doop = false;
            for (int pointer1 = 10; pointer1 < 20; pointer1++)
            {
                for (int pointer2 = 10; pointer2 < 20; pointer2++)
                {
                    if (pointer1 != pointer2)// No need to check pointer1 = pointer2; refers to same entry.
                    {
                        if (twentyCardList.get(pointer1) == twentyCardList.get(pointer2))
                        {
                            doop = true;
                            twentyCardList.set(pointer1, twentyCardList.get(randomNumberGenerator.nextInt(10))); // Add another random number to twentyCardList.
                        }
                    }
                }
            }
        }
    }
}
