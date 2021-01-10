import calcutils.BtnType;
import calcutils.CalcUtils;
import calcutils.Operator;
import calcutils.Special;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculatriceUi {

    private JTextField textField1;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton copyright;
    private JButton a7Button;
    private JButton egal;
    private JButton divide;
    private JButton a9Button;
    private JButton a8Button;
    private JButton eraseButton;
    private JButton a0Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton multiply;
    private JButton clearButton;
    private JButton plus;
    private JButton moduloButton;
    private JButton scientificButton;
    private JPanel mainWindow;
    private JButton minus;
    private JButton SINButton;
    private JButton SQRTButton;
    private JButton FACTButton;
    private JButton TANButton;
    private JButton COSButton;
    private JPanel JpanelScientific;

    private CalcUtils calcul = new CalcUtils();
    private BtnType lastPressed;


    public calculatriceUi() {

        for (int i = 0; i < UIManager.getInstalledLookAndFeels().length; i++)
            System.out.println(UIManager.getInstalledLookAndFeels()[i]);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JpanelScientific.setVisible(false);

        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(1);
            }
        });

        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(2);
            }
        });

        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(3);
            }
        });

        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(4);
            }
        });

        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(5);
            }
        });

        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(6);
            }
        });

        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(7);
            }
        });

        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(8);
            }
        });

        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(9);
            }
        });

        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberPressed(0);
            }
        });

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationPressed(Operator.PLUS);
            }
        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationPressed(Operator.MINUS);
            }
        });

        multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationPressed(Operator.MULTIPLY);
            }
        });

        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationPressed(Operator.DIVIDE);
            }
        });

        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //erase last digit
                textField1.setText(textField1.getText().substring(0, textField1.getText().length() - 1));
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //re-instantiate CalcUtils for a new calc
                calcul = new CalcUtils();
                //efface l'ecran
                clear();
            }
        });

        egal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcul.add(textField1.getText());
                textField1.setText(calcul.calculate());
                lastPressed = BtnType.EGAL;
            }
        });

        scientificButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!JpanelScientific.isVisible())
                    JpanelScientific.setVisible(true);
                else
                    JpanelScientific.setVisible(false);
            }
        });

        SINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialPressed(Special.SIN);
            }
        });

        COSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialPressed(Special.COS);
            }
        });

        TANButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialPressed(Special.TAN);
            }
        });

        SQRTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialPressed(Special.SQRT);
            }
        });

        FACTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialPressed(Special.FACT);
            }
        });

        moduloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationPressed(Operator.MODULO);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculatrice Java");
        frame.setContentPane(new calculatriceUi().mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * clean calculator screen
     */
    public void clear() {
        textField1.setText("");
    }

    /**
     * Print the value and refresh class state
     *
     * @param num Number pressed
     */
    public void numberPressed(int num) {
        //Si le dernier bouton pressé est une opération -> on efface
        if (lastPressed == BtnType.OPERATION)
            clear();
        //if EGAL is the last pressed button -> it's a new calculation
        if (lastPressed == BtnType.EGAL)
            clearButton.doClick();

        //Print number pressed
        textField1.setText(textField1.getText() + num);

        //Refresh lastPressed Button
        lastPressed = BtnType.NUMBER;
    }

    /**
     * refresh class state after an operation
     *
     * @param ope Operation pressed
     */
    public void operationPressed(Operator ope) {

        //sending last pressed number
        calcul.add(textField1.getText());

        //sending operation
        calcul.add(ope);

        //Refresh the last pressed Button
        lastPressed = BtnType.OPERATION;

    }

    /**
     * refresh class state after an one number operation
     *
     * @param spe Operation pressed
     */
    public void specialPressed(Special spe) {
        textField1.setText(calcul.specialCalcul(textField1.getText(), spe));
        lastPressed = BtnType.EGAL;
    }
}