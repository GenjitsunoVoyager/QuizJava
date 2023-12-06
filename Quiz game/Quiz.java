import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

//linha 100 onde fica o tamanho da tela
//Consultoria de Brocode/Tutorial

//Matrizes guardando as questões e respostas
public class Quiz implements ActionListener {

    String[] questions = {
            "Qual o nome do Autor de Fausto ?",
            "Nome do escudeiro de Don quixote:",
            "Por qual livro Maquiavel é conhecido ? ",
            "JRR Tolkien escreveu um desses livros ",
            "Demian é o pseudnome de qual deste autores",
            "Bram Stroken é o autor de",
            "A Comedia Divina,inicia a jornada na",
            "“Ser ou não ser, eis a questão.De qual livro é a mesma ?",
            "HP Lovecraft é o pai do genero:",
            "Mephisto deseja conseguir a alma de Fausto devido a ",
            "Qual o nome do protagonista de zelda ?",
            "Hideo Kojima é conhecido mundialmente por ",
            "Quem é o protagonista de Castlevania 1 snes ?",
            "Qual o primeiro jogo feito no mundo ?",
            "Quantos anos tinha Arthur Morgan em Red Dead Redemption 2 ?",
    };
    String[][] options = {
            { "Johann W Goethe", "Shakespeare", "Ismhael", "Miguel de Cervantes" }, // A
            { "Sancho Pança", "Rocinante", "Ricote", "Cervantes" }, // A
            { "O principe", "O rei", "O imperio", "Carta Magma" }, // A
            { "Eneida", "Narnia", "Meste Gil de Ham", "Hamlet" }, // C
            { "Virgilio", "Hernian Hesse", "Mary Bronks", "Khars" }, // B
            { "Drácula", "Carmilla", "Clark Kent", "Charlie Brown" }, // A
            { "Floresta Escura", "Inferno", "Purgatorio", "Paraiso" }, // A
            { "Romeu e Julieta", "Mcbeth", "Hamlet", "Júlio César" }, // B
            { "Terror Cosmico", "Horror Cosmico", "Romance Cosmico", "Drama Cosmico" }, // B
            { "Aposta", "Acordo", "Contrato", "Desrespeito" }, // A
            { "Link", "Zelda", "Ganondorf", "Épona" }, // A
            { "Metal Gear", "Snake Eater", "Pachinko", "Castway" }, // A
            { "Simon Belmont", "Richter Belmont", "Sarah Belmont", "Sophia Belmont" }, // A
            { "Pong", "Snake", "Space Invaders", "Donkey Kong" }, // A
            { "33", "35", "37", "36" },// D

    }; // Local onde está guardado as repostas corretas
    char[] answers = {
            'A',
            'A',
            'A',
            'C',
            'B',
            'A',
            'A',
            'B',
            'B',
            'A',
            'A',
            'A',
            'A',
            'A',
            'D'
    };
    // Logicas de endereços e afins e começo do FrameWork

    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 10;
    // construção dos butões e area de texto + tempo + quantidade de acertos +
    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if (seconds <= 0) { // caso não seja respondido em 10 segundos a resposta correta sera apresenta em
                                // verde
                displayAnswer();
            }
        }
    });

    // FrameWork Botões e Caixa pixel
    public Quiz() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sair ao fechar
        frame.setSize(1280, 720); // Resolução
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(null); // Capacidade de alterar alguma informação off
        frame.setResizable(false); // capacidade de alterar o tamanho da caixa

        textfield.setBounds(0, 0, 750, 50);
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);

        textarea.setBounds(0, 50, 750, 50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25, 25, 25));
        textarea.setForeground(new Color(25, 255, 0));
        textarea.setFont(new Font("MV Boli", Font.BOLD, 25));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);

        buttonA.setBounds(0, 100, 100, 100);
        buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 100, 100);
        buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 100, 100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 400, 100, 100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125, 100, 500, 100);
        answer_labelA.setBackground(new Color(50, 50, 50));
        answer_labelA.setForeground(new Color(25, 255, 0));
        answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answer_labelB.setBounds(125, 200, 500, 100);
        answer_labelB.setBackground(new Color(50, 50, 50));
        answer_labelB.setForeground(new Color(25, 255, 0));
        answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answer_labelC.setBounds(125, 300, 500, 100);
        answer_labelC.setBackground(new Color(50, 50, 50));
        answer_labelC.setForeground(new Color(25, 255, 0));
        answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answer_labelD.setBounds(125, 400, 500, 100);
        answer_labelD.setBackground(new Color(50, 50, 50));
        answer_labelD.setForeground(new Color(25, 255, 0));
        answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 35));

        seconds_left.setBounds(535, 510, 100, 100);
        seconds_left.setBackground(new Color(25, 25, 25));
        seconds_left.setForeground(new Color(255, 0, 0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535, 475, 100, 25);
        time_label.setBackground(new Color(50, 50, 50));
        time_label.setForeground(new Color(255, 0, 0));
        time_label.setFont(new Font("MV Boli", Font.PLAIN, 16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("Tempo");

        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(new Color(25, 25, 25));
        number_right.setForeground(new Color(25, 255, 0));
        number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25, 255, 0));
        percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);
        frame.add(textfield);
        frame.setVisible(true);
        // FrameWork da Questões e botões
        nextQuestion();
    }

    // + Logica para funcionamento do Quiz
    public void nextQuestion() {

        if (index >= total_questions) {
            results();
        } else {
            textfield.setText("Questão " + (index + 1));
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Logica
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource() == buttonA) {
            answer = 'A';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        displayAnswer();
    }

    public void displayAnswer() {

        timer.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'A')
            answer_labelA.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'B')
            answer_labelB.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'C')
            answer_labelC.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'D')
            answer_labelD.setForeground(new Color(255, 0, 0));

        Timer pause = new Timer(2000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                answer_labelA.setForeground(new Color(25, 255, 0));
                answer_labelB.setForeground(new Color(25, 255, 0));
                answer_labelC.setForeground(new Color(25, 255, 0));
                answer_labelD.setForeground(new Color(25, 255, 0));

                answer = ' ';
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void results() {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int) ((correct_guesses / (double) total_questions) * 100);

        textfield.setText("Lucas Ian Costa Nascimento");
        //
        //
        //
        //
        textarea.setText("Unifan Alfredo Nasser 23 ENG SOFT 2");
        answer_labelA.setText("BrennoPimenta");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("(" + correct_guesses + "/" + total_questions + ")");
        percentage.setText(result + "%");

        frame.add(number_right);
        frame.add(percentage);

    }
}