import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


class SimpleClass implements ActionListener {

    JFrame jFrm; int changingBalance; int changingScoreUser;
    JLabel balance; JLabel nameUser; JPanel mainPanel;
    JTextField takeBet; JLabel bet; int betFromUser;
    JButton jBtnYet; JButton jBtnStop; JButton jBtnStart;
    String [] cards = new String[]{"6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    int rndUser; String valueOfCardUser; int definition;
    String allUsersCards = ""; JLabel nameDealer; int changingScoreDealer;
    int rndDealer; String valueOfCardDealer; String allDealersCards = "";



    SimpleClass() {

        jFrm = new JFrame("OnMain");
        jFrm.setBounds(250, 80, 300, 400);
        jFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrm.setResizable(false);
        jFrm.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(161, 176, 255));

        jBtnYet = new JButton("YET");
        jBtnYet.setSize(260, 30);
        jBtnYet.setLocation(13, 280);
        jBtnStop = new JButton("STOP");
        jBtnStop.setSize(260, 30);
        jBtnStop.setLocation(13, 323);

        jBtnYet.setEnabled(false);
        jBtnStop.setEnabled(false);

        jBtnYet.setContentAreaFilled(false);
        jBtnYet.setForeground(new Color(0, 0, 0));
        jBtnYet.setFocusable(false);
        jBtnYet.addActionListener(this);
        jBtnYet.setPreferredSize(new Dimension(45, 45));
        jBtnStop.setContentAreaFilled(false);
        jBtnStop.setForeground(new Color(0, 0, 0));
        jBtnStop.setFocusable(false);
        jBtnStop.addActionListener(this);
        jBtnStop.setPreferredSize(new Dimension(45, 45));

        changingBalance = 2000;
        balance = new JLabel("balance: " + "  " + String.valueOf(changingBalance));
        balance.setSize(100, 15);
        balance.setLocation(180, 0);

        bet = new JLabel("Write your bet");
        takeBet = new JTextField(10);
        takeBet.setToolTipText("bet");
        bet.setSize(100, 15);
        bet.setLocation(2, 60);
        takeBet.setSize(100, 20);
        takeBet.setLocation(90, 60);
        jBtnStart = new JButton("PLAY");
        jBtnStart.setSize(70, 20);
        jBtnStart.setLocation(200, 60);
        jBtnStart.setContentAreaFilled(false);
        jBtnStart.setForeground(new Color(0, 0, 0));
        jBtnStart.setFocusable(false);
        jBtnStart.addActionListener(this);
        jBtnStart.setPreferredSize(new Dimension(45, 45));


        changingScoreUser = 0;
        nameUser = new JLabel("User: " + "" + String.valueOf(changingScoreUser));
        nameUser.setSize(200, 10);
        nameUser.setLocation(2, 130);
        changingScoreDealer = 0;
        nameDealer = new JLabel("Dealer: " + "" + String.valueOf(changingScoreDealer));
        nameDealer.setSize(200, 10);
        nameDealer.setLocation(2, 170);


        mainPanel.add(jBtnYet);
        mainPanel.add(jBtnStop);
        mainPanel.add(balance);
        mainPanel.add(nameUser);
        mainPanel.add(nameDealer);
        mainPanel.add(bet);
        mainPanel.add(takeBet);
        mainPanel.add(jBtnStart);
        mainPanel.setOpaque(true);
        jFrm.setContentPane(mainPanel);


    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("PLAY")) {

            if (takeBet.getText().equals("")) {

                String message = "You are not write anyone!";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else{

                try {
                    betFromUser = Integer.parseInt(takeBet.getText());
                }catch (NumberFormatException e){
                    String message = "You are write let's!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                if (betFromUser <= changingBalance & betFromUser > 0) {
                    changingBalance -= betFromUser;
                    balance.setText("balance: " + "  " + String.valueOf(changingBalance));
                    jBtnStart.setEnabled(false);
                    takeBet.setText("");
                    takeBet.setEnabled(false);
                    jBtnYet.setEnabled(true);
                    jBtnStop.setEnabled(true);
                    rndDealer = new Random().nextInt(cards.length);
                    valueOfCardDealer = cards[rndDealer];
                    int dealersDefinition = definition(valueOfCardDealer);
                    changingScoreDealer += dealersDefinition;
                    allDealersCards = allDealersCards + " " + valueOfCardDealer;
                    nameDealer.setText("Dealer: " + "" + String.valueOf(changingScoreDealer) +
                            "     " + "(" + allDealersCards + ")");
                    card();

                }

                else {
                    String message = "You are not write correct value!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        else if (ae.getActionCommand().equals("YET")) {
            card();

        }
        else{
            jBtnYet.setEnabled(false);
            jBtnStop.setEnabled(false);
            while (changingScoreDealer <= 16){
                rndDealer = new Random().nextInt(cards.length);
                valueOfCardDealer = cards[rndDealer];
                int dealersDefinition = definition(valueOfCardDealer);
                changingScoreDealer += dealersDefinition;
                allDealersCards = allDealersCards + " " + valueOfCardDealer;
                nameDealer.setText("Dealer: " + "" + String.valueOf(changingScoreDealer) +
                        "     " + "(" + allDealersCards + ")");
            }
            if (changingScoreDealer <= 21) {
                if (changingScoreDealer > changingScoreUser) {
                    jBtnStart.setEnabled(true);
                    takeBet.setEnabled(true);
                    String message = "You are lose";
                    JOptionPane.showMessageDialog(new JFrame(), message, "END",
                            JOptionPane.INFORMATION_MESSAGE);
                    changingScoreUser = 0;
                    changingScoreDealer = 0;
                    allUsersCards = "";
                    allDealersCards = "";
                    betFromUser = 0;
                    nameUser.setText("User: " + "" + String.valueOf(changingScoreUser));
                    nameDealer.setText("Dealer: " + "" + String.valueOf(changingScoreDealer));
                } else if (changingScoreDealer < changingScoreUser) {
                    jBtnStart.setEnabled(true);
                    takeBet.setEnabled(true);
                    String message = "You are win";
                    JOptionPane.showMessageDialog(new JFrame(), message, "END",
                            JOptionPane.INFORMATION_MESSAGE);
                    changingBalance += betFromUser * 2;
                    balance.setText("balance: " + "  " + String.valueOf(changingBalance));
                    changingScoreUser = 0;
                    changingScoreDealer = 0;
                    allUsersCards = "";
                    allDealersCards = "";
                    betFromUser = 0;
                    nameUser.setText("User: " + "" + String.valueOf(changingScoreUser));
                    nameDealer.setText("Dealer: " + "" + String.valueOf(changingScoreDealer));
                } else {
                    jBtnStart.setEnabled(true);
                    takeBet.setEnabled(true);
                    String message = "Draw";
                    JOptionPane.showMessageDialog(new JFrame(), message, "END",
                            JOptionPane.INFORMATION_MESSAGE);
                    changingBalance += betFromUser;
                    balance.setText("balance: " + "  " + String.valueOf(changingBalance));
                    changingScoreUser = 0;
                    changingScoreDealer = 0;
                    allUsersCards = "";
                    allDealersCards = "";
                    betFromUser = 0;
                    nameUser.setText("User: " + "" + String.valueOf(changingScoreUser));
                    nameDealer.setText("Dealer: " + "" + String.valueOf(changingScoreDealer));
                }
            }
            else {
                jBtnStart.setEnabled(true);
                takeBet.setEnabled(true);
                String message = "You are win";
                JOptionPane.showMessageDialog(new JFrame(), message, "END",
                        JOptionPane.INFORMATION_MESSAGE);
                changingBalance += betFromUser * 2;
                balance.setText("balance: " + "  " + String.valueOf(changingBalance));
                changingScoreUser = 0;
                changingScoreDealer = 0;
                allUsersCards = "";
                allDealersCards = "";
                betFromUser = 0;
                nameUser.setText("User: " + "" + String.valueOf(changingScoreUser));
                nameDealer.setText("Dealer: " + "" + String.valueOf(changingScoreDealer));
            }
        }
    }

    void card() {
        rndUser = new Random().nextInt(cards.length);
        valueOfCardUser = cards[rndUser];
        int usersDefinition = definition(valueOfCardUser);
        changingScoreUser += usersDefinition;
        allUsersCards = allUsersCards + " " + valueOfCardUser;
        nameUser.setText("User: " + "" + String.valueOf(changingScoreUser) +
                "     " + "(" + allUsersCards + ")");

        if (changingScoreUser > 21) {
            jBtnStart.setEnabled(true);
            takeBet.setEnabled(true);
            jBtnYet.setEnabled(false);
            jBtnStop.setEnabled(false);
            String message = "You are lose";
            JOptionPane.showMessageDialog(new JFrame(), message, "END",
                    JOptionPane.INFORMATION_MESSAGE);
            changingScoreUser = 0;
            changingScoreDealer = 0;
            allUsersCards = "";
            allDealersCards = "";
            betFromUser = 0;
            nameUser.setText("User: " + "" + String.valueOf(changingScoreUser));
            nameDealer.setText("Dealer: " + "" + String.valueOf(changingScoreDealer));

        }

    }

    int definition(String a){
        switch (a){
            case "6":
                definition = 6;
                break;
            case "7":
                definition = 7;
                break;
            case "8":
                definition = 8;
                break;
            case "9":
                definition = 9;
                break;
            case "10":
                definition = 10;
                break;
            case "J":
                definition = 2;
                break;
            case "Q":
                definition = 3;
                break;
            case "K":
                definition = 4;
                break;
            case "A":
                definition = 11;
                break;
        }
        return definition;
    }


    public static void main (String[]args){
        new SimpleClass();
    }

}

