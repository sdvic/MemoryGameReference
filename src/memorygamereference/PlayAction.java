package memorygamereference;

/**********************************************************************
 * Vic's version 10.0
 * September 2, 2010
 **********************************************************************/
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class PlayAction implements ActionListener, Runnable
{
    private int numberOfPairsExamined = 0;
    private int numberOfCorrectCardPairMatches = 0;
    private int turnedOverCardCount = 0;
    private ImageIcon coverIcon = new ImageIcon("/Users/george/NetBeansProjects/ShrutisMemoryGame/src/mysimplememorygame/images/CardCover.jpg");  // Make an icon out of the cover image
    private JButton selectedButton;
    private int buttonNumber;
    private ImageIcon selectedIcon;
    private int firstCardNumber;
    private int secondCardNumber;
    private JButton firstButtonSelected;
    private JButton secondButtonSelected;
    private JFrame mainWindow;
    private ArrayList<Integer> twentyCardList;

    public void actionPerformed(ActionEvent e)
    {
        selectedButton = (JButton)e.getSource();
        buttonNumber = selectedButton.getMnemonic(); //Get button number
        turnedOverCardCount++;
        selectedIcon = new ImageIcon("/Users/george/NetBeansProjects/ShrutisMemoryGame/src/mysimplememorygame/images/Card" + twentyCardList.get(buttonNumber) + ".jpg");
        selectedButton.setIcon(selectedIcon);
        if (turnedOverCardCount == 1) // Check click count and store the corresponding values
        {
            firstButtonSelected = selectedButton;
            firstCardNumber = twentyCardList.get(buttonNumber);
        }
        if (turnedOverCardCount == 2)
        {
            secondButtonSelected = selectedButton;
            secondCardNumber = twentyCardList.get(buttonNumber);
            numberOfPairsExamined++;
            if (firstCardNumber == secondCardNumber)
            {
                numberOfCorrectCardPairMatches++;
                turnedOverCardCount = 0;
                if (numberOfCorrectCardPairMatches == 10) // If all pairs are found then game is over
                {
                    JOptionPane.showMessageDialog(null, "Congratulations you won in " + numberOfPairsExamined + " tries");
                    System.exit(0);
                }
            }
            if (firstCardNumber != secondCardNumber)
            {
                mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                SwingUtilities.invokeLater(this); // To let graphics stuff run first before game logic.
            }
        }
    }

    public void run() // Runs after all painting done because of SwingUtilities.invokeLater(this).
    {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException ex)
        {
        }
        firstButtonSelected.setIcon(coverIcon);
        secondButtonSelected.setIcon(coverIcon);
        turnedOverCardCount = 0;
        mainWindow.setCursor(Cursor.getDefaultCursor());
    }

    public void setMainWindow(JFrame mainWindow)
    {
        this.mainWindow = mainWindow;
    }

    public void setTwentyCardList(ArrayList<Integer> twentyCardList)
    {
        this.twentyCardList = twentyCardList;
    }
}
