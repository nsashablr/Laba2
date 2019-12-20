package L2S;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.*;

public class MainFrame extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldMem1;
    private JTextField textFieldMem2;
    private JTextField textFieldMem3;
    private JTextField textFieldResult;
    private JLabel labelImg = new JLabel();
    private ButtonGroup radioButtonsFormulaType = new ButtonGroup();
    private ButtonGroup radioButtonsMem = new ButtonGroup();
    private Box hBoxMem;
    private Box hBoxFormulaType;
    private int formulaId = 1;
    private int memId = 1;
    private Double doubleMem1 = 0.0;
    private Double doubleMem2 = 0.0;
    private Double doubleMem3 = 0.0;
    private Image img1 = ImageIO.read(new File("src/img1.bmp"));
    private Image img2 = ImageIO.read(new File("src/img2.bmp"));
    private Double calculate1(Double x, Double y, Double z) {
        return pow((log(z) + sin(PI*z*z)), 0.25)/pow((y*y + pow (E, cos(x)) + sin(y)), sin(x));
    }
    private Double calculate2(Double x, Double y, Double z) {
        return pow(y, 0.5)*(3*pow(z, x))/pow((1+y*y*y), 0.5);
    }
    private void addRadioButtonFormula(String buttonName, final int buttonId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ev) {
                MainFrame.this.formulaId = buttonId;
                // вывод изображения в зависимости от выбранной кнопки
                if (MainFrame.this.formulaId == 1) {
                    labelImg.setIcon(new ImageIcon(img1));
                }
                if (MainFrame.this.formulaId == 2) {
                    labelImg.setIcon(new ImageIcon(img2));
                }
            }
        });
        radioButtonsFormulaType.add(button);
        hBoxFormulaType.add(button);
    }
    private void addRadioButtonMem(String buttonName, final int memId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.memId = memId;
                if (memId == 1) {
                    textFieldMem1.setText(doubleMem1.toString());
                }
                else if (memId == 2) {
                    textFieldMem2.setText(doubleMem2.toString());
                }
                else if (memId == 3) {
                    textFieldMem3.setText(doubleMem3.toString());
                }
            }
        });
        radioButtonsMem.add(button);
        hBoxMem.add(button);
    }
    public MainFrame() throws IOException {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
        // box с изображением формулы
        labelImg.setIcon(new ImageIcon(img1));
        Box hBoxImg = Box.createHorizontalBox();
        hBoxImg.add(Box.createHorizontalGlue());
        hBoxImg.add(labelImg);
        hBoxImg.add(Box.createHorizontalGlue());
        hBoxImg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // box с формулами
        hBoxFormulaType = Box.createHorizontalBox();
        hBoxFormulaType.add(Box.createHorizontalGlue());
        addRadioButtonFormula("Формула 1", 1);
        addRadioButtonFormula("Формула 2", 2);
        radioButtonsFormulaType.setSelected(radioButtonsFormulaType.getElements().nextElement().getModel(), true);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        hBoxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // box для значенией x, y, z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0.0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0.0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0.0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hBoxVariables = Box.createHorizontalBox();
        hBoxVariables.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hBoxVariables.add(Box.createHorizontalGlue());
        hBoxVariables.add(labelForX);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldX);
        hBoxVariables.add(Box.createHorizontalStrut(30));
        hBoxVariables.add(labelForY);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldY);
        hBoxVariables.add(Box.createHorizontalStrut(30));
        hBoxVariables.add(labelForZ);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldZ);
        hBoxVariables.add(Box.createHorizontalGlue());
        // box выбора и отображения ячеек памяти
        hBoxMem = Box.createHorizontalBox();
        hBoxMem.add(Box.createHorizontalGlue());
        textFieldMem1 = new JTextField("0.0", 15);
        textFieldMem1.setMaximumSize(textFieldMem1.getPreferredSize());
        textFieldMem2 = new JTextField("0.0", 15);
        textFieldMem2.setMaximumSize(textFieldMem2.getPreferredSize());
        textFieldMem3 = new JTextField("0.0", 15);
        textFieldMem3.setMaximumSize(textFieldMem3.getPreferredSize());
        hBoxMem = Box.createHorizontalBox();
        hBoxMem.add(Box.createHorizontalGlue());
        addRadioButtonMem("Переменная 1", 1);
        hBoxMem.add(Box.createHorizontalStrut(10));
        hBoxMem.add(textFieldMem1);
        hBoxMem.add(Box.createHorizontalStrut(15));
        addRadioButtonMem("Переменная 2", 2);
        hBoxMem.add(Box.createHorizontalStrut(10));
        hBoxMem.add(textFieldMem2);
        hBoxMem.add(Box.createHorizontalStrut(15));
        addRadioButtonMem("Переменная 3", 3);
        radioButtonsMem.setSelected(radioButtonsMem.getElements().nextElement().getModel(), true);
        hBoxMem.add(Box.createHorizontalStrut(10));
        hBoxMem.add(textFieldMem3);
        hBoxMem.add(Box.createHorizontalGlue());
        hBoxMem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // box для результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0.0", 20);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.add(labelForResult);
        hBoxResult.add(Box.createHorizontalStrut(10));
        hBoxResult.add(textFieldResult);
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // кнопки очистки, вычисления результата и управления памятью
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
            } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
        }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0.0");
                textFieldY.setText("0.0");
                textFieldZ.setText("0.0");
                textFieldResult.setText("0.0");
                textFieldMem1.setText("0.0");
                textFieldMem2.setText("0.0");
                textFieldMem3.setText("0.0");
                doubleMem1 = 0.0;
                doubleMem2 = 0.0;
                doubleMem3 = 0.0;
            }
        });
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(memId == 1) {
                    doubleMem1 = 0.0;
                    textFieldMem1.setText("0.0");
                }
                if(memId == 2) {
                    doubleMem2 = 0.0;
                    textFieldMem2.setText("0.0");
                }
                if(memId == 3) {
                    doubleMem3 = 0.0;
                    textFieldMem3.setText("0.0");
                }
            }
        });
        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Double result = Double.parseDouble(textFieldResult.getText());
                if (memId == 1) {
                    doubleMem1 += result;
                    textFieldMem1.setText(Double.toString(doubleMem1));
                }
                else if (memId == 2) {
                    doubleMem2 += result;
                    textFieldMem2.setText(Double.toString(doubleMem2));
                }
                else if (memId == 3) {
                    doubleMem3 += result;
                    textFieldMem3.setText(Double.toString(doubleMem3));
                }
            }
        });
        // box для кнопок очистки, вычисления результата и управления памятью
        Box hBoxButtons = Box.createHorizontalBox();
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.add(buttonCalc);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(buttonReset);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(buttonMC);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(buttonMPlus);
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // сборка всех box в один вертикальный box
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxImg);
        contentBox.add(hBoxFormulaType);
        contentBox.add(hBoxVariables);
        contentBox.add(hBoxMem);
        contentBox.add(hBoxResult);
        contentBox.add(hBoxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws IOException {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}