import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Poke_generation extends JFrame {

    // フォントの設定
    Font newFont = new Font("M S ゴシック", Font.BOLD, 20);
    
    // 発売日と現在時刻の差を表示するラベルを定義
    JLabel differenceLabel;

    // 発売日を表示するラベルを定義
    JLabel specificTimeLabel;

    // 差の時間を変換するボタンを定義
    JButton conversionButton;

    // 発売日を定義
    LocalDateTime specificTime;

    // 現在時刻のラベルを定義
    JLabel timeLabel;
    
    // メニューボタンを定義
    JButton buttonS,buttonM;

    // ボタンクリックで時間変更するフラグを定義
    int flag = 1;

    public Poke_generation() {
        // 初期画面を表示
        main();
    }
    
    // もろもろ関数
    public void menu() {
        // flagの初期化
        flag = 1;

        // 既存のコンテンツを削除
        getContentPane().removeAll();
        // 新しいコンテンツを設定
        // メニューパネルを作成
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); //左側に生成

        // メニューボタンを作成
        Dimension buttonsize = new Dimension(100,50);
        buttonM = new JButton("本編");
        buttonM.setFont(new Font("M S ゴシック",Font.BOLD,15));
        buttonS = new JButton("外伝");
        buttonS.setFont(new Font("M S ゴシック",Font.BOLD,15));
        buttonM.setPreferredSize(buttonsize);
        buttonS.setPreferredSize(buttonsize);
        
        // ボタンをパネルに追加
        menuPanel.add(buttonM);
        menuPanel.add(buttonS);

        // 現在時刻ラベルを追加
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial",Font.BOLD,30));
        menuPanel.add(timeLabel);

        // GIFを追加
        ImageIcon image = new ImageIcon("images/myu.gif");
        JLabel imageLabel = new JLabel(image);
        menuPanel.add(imageLabel);

        // タイマーの設定 (1000ミリ秒ごとに更新)
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();

        // パネルをフレームに追加
        getContentPane().add(menuPanel,BorderLayout.NORTH);
    }

    // 発売日と差のラベル関数
    private void difference_label() {
        differenceLabel = new JLabel();
        differenceLabel.setFont(new Font("M S ゴシック",Font.BOLD,30));
        differenceLabel.setHorizontalAlignment(JLabel.CENTER);
        specificTimeLabel = new JLabel();
        specificTimeLabel.setFont(new Font("M S ゴシック",Font.BOLD,30));
        specificTimeLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    // 時間変換ボタン関数
    private void conversion() {
        conversionButton = new JButton("変換");
        Font newFont = new Font("M S ゴシック",Font.BOLD,30);
        conversionButton.setFont(newFont);
        conversionButton.setHorizontalAlignment(JButton.CENTER);
        conversionButton.setPreferredSize(new Dimension(100,70));

        conversionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                flag++;
                if(flag>5) {
                    flag = 1;
                }
            }
        });
    }
    
    // 本編と外伝ボタンクリック時のアクション
    private void buttonMS() {
        buttonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main();
            }
        });

        buttonS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                sub();
            }
        });
    }

    // 差を表示の仕方の関数
    private void difference() {
        // タイマーの設定 (1秒ごとに更新)
        Timer timer1 = new Timer(1000, e -> {
            LocalDateTime now = LocalDateTime.now();

            Duration duration = Duration.between(specificTime, now);
            long seconds = duration.getSeconds();
            long absSeconds = Math.abs(seconds);
            
            // それぞれの表示の仕方を定義
            String positive1 = String.format(
                "%d年%d日%d時間%d分%d秒",
                absSeconds / 31536000,
                (absSeconds % 31536000) / 86400,
                (absSeconds % 86400) / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60
            );

            String positive2 = String.format(
                "%d日%d時間%d分%d秒",
                absSeconds / 86400,
                (absSeconds % 86400) / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60
            );

            String positive3 = String.format(
                "%d時間%d分%d秒",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60
            );

            String positive4 = String.format(
                "%d分%d秒",
                absSeconds / 60,
                absSeconds % 60
            );

            String positive5 = String.format(
                "%d秒",
                absSeconds
            );

            // フラグによって表示の仕方を変化
            String differenceText;
            if (flag == 1) {
                differenceText = "発売日から現在まで: " + positive1;
                differenceLabel.setText(differenceText);
            } else if(flag == 2){
                differenceText = "発売日から現在まで: " + positive2;
                differenceLabel.setText(differenceText);
            } else if (flag == 3) {
                differenceText = "発売日から現在まで: " + positive3;
                differenceLabel.setText(differenceText);
            }else if (flag == 4) {
                differenceText = "発売日から現在まで: " + positive4;
                differenceLabel.setText(differenceText);
            }else if (flag == 5) {
                differenceText = "発売日から現在まで: " + positive5;
                differenceLabel.setText(differenceText);
            }

        });
        timer1.start();
    }

    private void main() {
   
        // もろもろ関数
        menu();

        // 世代パネルを作成
        JPanel gPanel = new JPanel(new GridLayout(3, 3));// 3x3のグリッドレイアウトを設定

        //世代ボタンを作成
        ImageIcon g1 = new ImageIcon("images/170x170/red_poke.jpg");
        JButton b1 = new JButton("1世代",g1);
        b1.setFont(newFont);
        // テキストを画像の下に配置
        b1.setHorizontalTextPosition(SwingConstants.CENTER);
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g2 = new ImageIcon("images/170x170/gold_poke.jpg");
        JButton b2 = new JButton("2世代",g2);
        b2.setFont(newFont);
        // テキストを画像の下に配置
        b2.setHorizontalTextPosition(SwingConstants.CENTER);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g3 = new ImageIcon("images/170x170/ruby_poke.jpg");
        JButton b3 = new JButton("3世代",g3);
        b3.setFont(newFont);
        // テキストを画像の下に配置
        b3.setHorizontalTextPosition(SwingConstants.CENTER);
        b3.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g4 = new ImageIcon("images/170x170/diamond_poke.jpg");
        JButton b4 = new JButton("4世代",g4);
        b4.setFont(newFont);
        // テキストを画像の下に配置
        b4.setHorizontalTextPosition(SwingConstants.CENTER);
        b4.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g5 = new ImageIcon("images/170x170/black_poke.jpg");
        JButton b5 = new JButton("5世代",g5);
        b5.setFont(newFont);
        // テキストを画像の下に配置
        b5.setHorizontalTextPosition(SwingConstants.CENTER);
        b5.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g6 = new ImageIcon("images/170x170/x_poke.jpg");
        JButton b6 = new JButton("6世代",g6);
        b6.setFont(newFont);
        // テキストを画像の下に配置
        b6.setHorizontalTextPosition(SwingConstants.CENTER);
        b6.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g7 = new ImageIcon("images/170x170/sun_poke.jpg");
        JButton b7 = new JButton("7世代",g7);
        b7.setFont(newFont);
        // テキストを画像の下に配置
        b7.setHorizontalTextPosition(SwingConstants.CENTER);
        b7.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g8 = new ImageIcon("images/170x170/sword_poke.jpg");
        JButton b8 = new JButton("8世代",g8);
        b8.setFont(newFont);
        // テキストを画像の下に配置
        b8.setHorizontalTextPosition(SwingConstants.CENTER);
        b8.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g9 = new ImageIcon("images/170x170/scarlet_poke.jpg");
        JButton b9 = new JButton("9世代",g9);
        b9.setFont(newFont);
        // テキストを画像の下に配置
        b9.setHorizontalTextPosition(SwingConstants.CENTER);
        b9.setVerticalTextPosition(SwingConstants.BOTTOM);

        //ボタンをパネルに追加
        gPanel.add(b1);
        gPanel.add(b2);
        gPanel.add(b3);
        gPanel.add(b4);
        gPanel.add(b5);
        gPanel.add(b6);
        gPanel.add(b7);
        gPanel.add(b8);
        gPanel.add(b9);

        // ボタンクリックアクション
        buttonS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sub();
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g1();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g2();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g3();
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g4();
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g5();
            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g6();
            }
        });

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g7();
            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g8();
            }
        });

        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g9();
            }
        });

        // パネルをフレームに追加
        getContentPane().add(gPanel);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void sub() {
        // もろもろ関数
        menu();

        // 世代パネルを作成
        JPanel gPanel = new JPanel(new GridLayout(2, 2));// 2x2のグリッドレイアウトを設定

        //世代ボタンを作成
        ImageIcon g1 = new ImageIcon("images/190x190/gb_poke.jpg");
        JButton b1 = new JButton("1990年代",g1);
        b1.setFont(newFont);
        // テキストを画像の下に配置
        b1.setHorizontalTextPosition(SwingConstants.CENTER);
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g2 = new ImageIcon("images/190x190/coliseum_poke.jpg");
        JButton b2 = new JButton("2000年代",g2);
        b2.setFont(newFont);
        // テキストを画像の下に配置
        b2.setHorizontalTextPosition(SwingConstants.CENTER);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g3 = new ImageIcon("images/190x190/usun_poke.jpg");
        JButton b3 = new JButton("2010年代",g3);
        b3.setFont(newFont);
        // テキストを画像の下に配置
        b3.setHorizontalTextPosition(SwingConstants.CENTER);
        b3.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon g4 = new ImageIcon("images/190x190/a_legends_poke.jpg");
        JButton b4 = new JButton("2020年代",g4);
        b4.setFont(newFont);
        // テキストを画像の下に配置
        b4.setHorizontalTextPosition(SwingConstants.CENTER);
        b4.setVerticalTextPosition(SwingConstants.BOTTOM);

        //ボタンをパネルに追加
        gPanel.add(b1);
        gPanel.add(b2);
        gPanel.add(b3);
        gPanel.add(b4);

        // ボタンクリックアクション
        buttonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main();
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g1sub();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g2sub();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g3sub();
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                g4sub();
            }
        });

        // パネルをフレームに追加
        getContentPane().add(gPanel);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g1(){
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/red_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g1a = new ImageIcon("images/300x300/green_poke.jpg");
        JLabel label2 = new JLabel(g1a);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(1996, 2, 27, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g2(){
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/gold_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g1a = new ImageIcon("images/300x300/silver_poke.jpg");
        JLabel label2 = new JLabel(g1a);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(1999, 11, 21, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }


    private void g3(){
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/ruby_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g1a = new ImageIcon("images/300x300/sapphire_poke.jpg");
        JLabel label2 = new JLabel(g1a);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2002, 11, 21, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g4(){
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/diamond_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g1a = new ImageIcon("images/300x300/pearl_poke.jpg");
        JLabel label2 = new JLabel(g1a);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2006, 9, 28, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g5(){
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/black_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g1a = new ImageIcon("images/300x300/white_poke.jpg");
        JLabel label2 = new JLabel(g1a);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2010, 9, 18, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g6(){
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/x_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g1a = new ImageIcon("images/300x300/y_poke.jpg");
        JLabel label2 = new JLabel(g1a);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2013, 10, 12, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();


        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g7(){
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/sun_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g1a = new ImageIcon("images/300x300/moon_poke.jpg");
        JLabel label2 = new JLabel(g1a);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2016, 11, 18, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g8(){
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/sword_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g1a = new ImageIcon("images/300x300/shield_poke.jpg");
        JLabel label2 = new JLabel(g1a);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2019, 11, 15, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g9(){
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/scarlet_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g1a = new ImageIcon("images/300x300/violet_poke.jpg");
        JLabel label2 = new JLabel(g1a);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2022, 11, 18, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g1sub(){
        // もろもろ関数
        menu();

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 世代パネルを作成
        JPanel gPanel = new JPanel(new GridLayout(2, 2));// 2x2のグリッドレイアウトを設定

        //世代ボタンを作成
        ImageIcon blue = new ImageIcon("images/190x190/blue_poke.jpg");
        JButton b1 = new JButton("青",blue);
        b1.setFont(newFont);
        // テキストを画像の下に配置
        b1.setHorizontalTextPosition(SwingConstants.CENTER);
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon stadium = new ImageIcon("images/190x190/stadium_poke.jpg");
        JButton b2 = new JButton("スタジアム",stadium);
        b2.setFont(newFont);
        // テキストを画像の下に配置
        b2.setHorizontalTextPosition(SwingConstants.CENTER);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon pica = new ImageIcon("images/190x190/pika_poke.jpg");
        JButton b3 = new JButton("ピカチュウ",pica);
        b3.setFont(newFont);
        // テキストを画像の下に配置
        b3.setHorizontalTextPosition(SwingConstants.CENTER);
        b3.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon gb = new ImageIcon("images/190x190/gb_poke.jpg");
        JButton b4 = new JButton("GB",gb);
        b4.setFont(newFont);
        // テキストを画像の下に配置
        b4.setHorizontalTextPosition(SwingConstants.CENTER);
        b4.setVerticalTextPosition(SwingConstants.BOTTOM);

        //ボタンをパネルに追加
        gPanel.add(b1);
        gPanel.add(b2);
        gPanel.add(b3);
        gPanel.add(b4);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                blue();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                stadium();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                pika();
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gb();
            }
        });

        // パネルをフレームに追加
        getContentPane().add(gPanel);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void blue() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/blue_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(1996, 10, 15, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void stadium() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/stadium_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(1998, 8, 1, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void pika() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/pika_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(1998, 9, 12, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void gb() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/gb_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(1998, 12, 18, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g2sub(){

        // もろもろ関数
        menu();

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 世代パネルを作成
        JPanel gPanel = new JPanel(new GridLayout(3, 4));// 3x4のグリッドレイアウトを設定

        //世代ボタンを作成
        ImageIcon crystal = new ImageIcon("images/120x120/crystal_poke.jpg");
        JButton b1 = new JButton("クリスタル",crystal);
        b1.setFont(newFont);
        // テキストを画像の下に配置
        b1.setHorizontalTextPosition(SwingConstants.CENTER);
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon gb2 = new ImageIcon("images/120x120/gb2_poke.jpg");
        JButton b2 = new JButton("GB2",gb2);
        b2.setFont(newFont);
        // テキストを画像の下に配置
        b2.setHorizontalTextPosition(SwingConstants.CENTER);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon coliseum = new ImageIcon("images/120x120/coliseum_poke.jpg");
        JButton b3 = new JButton("コロシアム",coliseum);
        b3.setFont(newFont);
        // テキストを画像の下に配置
        b3.setHorizontalTextPosition(SwingConstants.CENTER);
        b3.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon frlg = new ImageIcon("images/120x120/r_red_poke.jpg");
        JButton b4 = new JButton("FRLG",frlg);
        b4.setFont(newFont);
        // テキストを画像の下に配置
        b4.setHorizontalTextPosition(SwingConstants.CENTER);
        b4.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon em = new ImageIcon("images/120x120/emerald_poke.jpg");
        JButton b5 = new JButton("エメラルド",em);
        b5.setFont(newFont);
        // テキストを画像の下に配置
        b5.setHorizontalTextPosition(SwingConstants.CENTER);
        b5.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon xd = new ImageIcon("images/120x120/xd_poke.jpg");
        JButton b6 = new JButton("XD",xd);
        b6.setFont(newFont);
        // テキストを画像の下に配置
        b6.setHorizontalTextPosition(SwingConstants.CENTER);
        b6.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon red_blue = new ImageIcon("images/120x120/wonder_red_poke.jpg");
        JButton b7 = new JButton("ポケダン赤・青",red_blue);
        b7.setFont(newFont);
        // テキストを画像の下に配置
        b7.setHorizontalTextPosition(SwingConstants.CENTER);
        b7.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon ranger = new ImageIcon("images/120x120/ranger_poke.png");
        JButton b8 = new JButton("ポケモンレンジャー",ranger);
        b8.setFont(newFont);
        // テキストを画像の下に配置
        b8.setHorizontalTextPosition(SwingConstants.CENTER);
        b8.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon time_drak = new ImageIcon("images/120x120/wonder_time_poke.png");
        JButton b9 = new JButton("ポケダン時・闇",time_drak);
        b9.setFont(newFont);
        // テキストを画像の下に配置
        b9.setHorizontalTextPosition(SwingConstants.CENTER);
        b9.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon platinum = new ImageIcon("images/120x120/platinum_poke.png");
        JButton b10 = new JButton("プラチナ",platinum);
        b10.setFont(newFont);
        // テキストを画像の下に配置
        b10.setHorizontalTextPosition(SwingConstants.CENTER);
        b10.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon sky = new ImageIcon("images/120x120/wonder_sky_poke.jpg");
        JButton b11 = new JButton("ポケダン空",sky);
        b11.setFont(newFont);
        // テキストを画像の下に配置
        b11.setHorizontalTextPosition(SwingConstants.CENTER);
        b11.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon hgss = new ImageIcon("images/120x120/r_gold_poke.jpg");
        JButton b12 = new JButton("FGSS",hgss);
        b12.setFont(newFont);
        // テキストを画像の下に配置
        b12.setHorizontalTextPosition(SwingConstants.CENTER);
        b12.setVerticalTextPosition(SwingConstants.BOTTOM);

        //ボタンをパネルに追加
        gPanel.add(b1);
        gPanel.add(b2);
        gPanel.add(b3);
        gPanel.add(b4);
        gPanel.add(b5);
        gPanel.add(b6);
        gPanel.add(b7);
        gPanel.add(b8);
        gPanel.add(b9);
        gPanel.add(b10);
        gPanel.add(b11);
        gPanel.add(b12);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                crystal();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gb2();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                coliseum();
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frlg();
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                em();
            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                xd();
            }
        });

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                blue_red();
            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ranger();
            }
        });

        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                time_drak();
            }
        });

        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                platinum();
            }
        });

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                sky();
            }
        });

        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                hgss();
            }
        });

        // パネルをフレームに追加
        getContentPane().add(gPanel);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void crystal() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/crystal_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2000, 12, 14, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void gb2() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/gb2_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2001, 3, 28, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void coliseum() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/coliseum_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2003, 11, 21, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void frlg() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/r_red_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/r_green_poke.jpg");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2004, 1, 29, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void em() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/emerald_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2004, 9, 16, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void xd() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/xd_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2005, 8, 4, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void blue_red() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/wonder_blue_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/wonder_red_poke.jpg");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2005, 11, 17, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void ranger() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/ranger_poke.png");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2006, 3, 23, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void time_drak() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/wonder_time_poke.png");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/wonder_dark_poke.png");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2007, 9, 13, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void platinum() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/platinum_poke.png");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2008, 9, 13, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void sky() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/wonder_sky_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2009, 4, 18, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void hgss() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/r_gold_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/r_silver_poke.jpg");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2009, 9, 12, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g3sub(){
        // もろもろ関数
        menu();

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 世代パネルを作成
        JPanel gPanel = new JPanel(new GridLayout(2, 4));// 3x4のグリッドレイアウトを設定

        //世代ボタンを作成
        ImageIcon bw2 = new ImageIcon("images/120x120/black2_poke.jpg");
        JButton b1 = new JButton("BW2",bw2);
        b1.setFont(newFont);
        // テキストを画像の下に配置
        b1.setHorizontalTextPosition(SwingConstants.CENTER);
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon oras = new ImageIcon("images/120x120/r_ruby_poke.jpg");
        JButton b2 = new JButton("ORAS",oras);
        b2.setFont(newFont);
        // テキストを画像の下に配置
        b2.setHorizontalTextPosition(SwingConstants.CENTER);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon hyper = new ImageIcon("images/120x120/wonder_hyper_poke.jpg");
        JButton b3 = new JButton("超ポケダン",hyper);
        b3.setFont(newFont);
        // テキストを画像の下に配置
        b3.setHorizontalTextPosition(SwingConstants.CENTER);
        b3.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon vc1 = new ImageIcon("images/120x120/vc_red_poke.png");
        JButton b4 = new JButton("VC赤緑青ピカ",vc1);
        b4.setFont(newFont);
        // テキストを画像の下に配置
        b4.setHorizontalTextPosition(SwingConstants.CENTER);
        b4.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon vc2 = new ImageIcon("images/120x120/vc_gold_poke.png");
        JButton b5 = new JButton("VC金銀",vc2);
        b5.setFont(newFont);
        // テキストを画像の下に配置
        b5.setHorizontalTextPosition(SwingConstants.CENTER);
        b5.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon usum = new ImageIcon("images/120x120/usun_poke.jpg");
        JButton b6 = new JButton("USUM",usum);
        b6.setFont(newFont);
        // テキストを画像の下に配置
        b6.setHorizontalTextPosition(SwingConstants.CENTER);
        b6.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon vc3 = new ImageIcon("images/120x120/vc_crystal_poke.png");
        JButton b7 = new JButton("VCクリスタル",vc3);
        b7.setFont(newFont);
        // テキストを画像の下に配置
        b7.setHorizontalTextPosition(SwingConstants.CENTER);
        b7.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon p_e = new ImageIcon("images/120x120/r_pika_poke.jpg");
        JButton b8 = new JButton("ピカブイ",p_e);
        b8.setFont(newFont);
        // テキストを画像の下に配置
        b8.setHorizontalTextPosition(SwingConstants.CENTER);
        b8.setVerticalTextPosition(SwingConstants.BOTTOM);

        //ボタンをパネルに追加
        gPanel.add(b1);
        gPanel.add(b2);
        gPanel.add(b3);
        gPanel.add(b4);
        gPanel.add(b5);
        gPanel.add(b6);
        gPanel.add(b7);
        gPanel.add(b8);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                bw2();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                oras();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                hyper();
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                vc1();
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                vc2();
            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                usum();
            }
        });

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                vc3();
            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                p_e();
            }
        });

        // パネルをフレームに追加
        getContentPane().add(gPanel);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void bw2() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/black2_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/white2_poke.jpg");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2012, 6, 23, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void oras() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/r_ruby_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/r_sapphire_poke.jpg");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2014, 11, 21, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void hyper() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/wonder_hyper_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2015, 9, 17, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void vc1() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/vc_red_poke.png");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/vc_green_poke.png");
        JLabel label1 = new JLabel(g2);
        ImageIcon g3 = new ImageIcon("images/300x300/vc_blue_poke.png");
        JLabel label2 = new JLabel(g3);
        ImageIcon g4 = new ImageIcon("images/300x300/vc_pika_poke.png");
        JLabel label3 = new JLabel(g4);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);
        imagePanel.add(label2);
        imagePanel.add(label3);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2016, 2, 27, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void vc2() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/vc_gold_poke.png");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/vc_silver_poke.png");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2017, 9, 22, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void usum() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/usun_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/umoo_poke.jpg");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2017, 11, 17, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void vc3() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/vc_crystal_poke.png");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2018, 1, 26, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void p_e() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/r_pika_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/r_eev.jpg");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2018, 11, 16, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void g4sub(){
        // もろもろ関数
        menu();

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 世代パネルを作成
        JPanel gPanel = new JPanel(new GridLayout(1, 2));// 1x2のグリッドレイアウトを設定

        //世代ボタンを作成
        ImageIcon bdsp = new ImageIcon("images/100x160/r_diamond_poke.jpg");
        JButton b1 = new JButton("BDSP",bdsp);
        b1.setFont(newFont);
        // テキストを画像の下に配置
        b1.setHorizontalTextPosition(SwingConstants.CENTER);
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon legends1 = new ImageIcon("images/100x160/a_legends_poke.jpg");
        JButton b2 = new JButton("レジェンズアルセウス",legends1);
        b2.setFont(newFont);
        // テキストを画像の下に配置
        b2.setHorizontalTextPosition(SwingConstants.CENTER);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);

        //ボタンをパネルに追加
        gPanel.add(b1);
        gPanel.add(b2);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                bdsp();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                legends1();
            }
        });

        // パネルをフレームに追加
        getContentPane().add(gPanel);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void bdsp() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/r_diamond_poke.jpg");
        JLabel label = new JLabel(g1);
        ImageIcon g2 = new ImageIcon("images/300x300/r_pearl_poke.jpg");
        JLabel label1 = new JLabel(g2);

        // 画像をパネルに追加
        imagePanel.add(label);
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2021, 11, 19, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void legends1() {
        // もろもろ関数
        menu();

        // 画像パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));

        // 時刻パネルを作成
        JPanel timePanel = new JPanel(new GridLayout(3, 1));

        // 画像を添付
        ImageIcon g1 = new ImageIcon("images/300x300/a_legends_poke.jpg");
        JLabel label = new JLabel(g1);

        // 画像をパネルに追加
        imagePanel.add(label);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 発売日
        specificTime = LocalDateTime.of(2022, 1, 28, 0, 0, 0);
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差を表示の仕方の関数
        difference();

        // 時間変換ボタン関数
        conversion();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        getContentPane().add(timePanel,BorderLayout.SOUTH);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    // 現在時刻を更新するメソッド
    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss                                     ");
        timeLabel.setText(now.format(formatter));
    }
}