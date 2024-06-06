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
    JLabel differenceLabel = new JLabel();

    // 発売日を表示するラベルを定義
    JLabel specificTimeLabel = new JLabel();

    // 差の時間を変換するボタンを定義
    JButton conversionButton = new JButton("変換");

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
    private void menu() {
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

        // 画像とラベルを設定
        String[] images = {
            "images/170x170/red_poke.jpg",
            "images/170x170/gold_poke.jpg",
            "images/170x170/ruby_poke.jpg",
            "images/170x170/diamond_poke.jpg",
            "images/170x170/black_poke.jpg",
            "images/170x170/x_poke.jpg",
            "images/170x170/sun_poke.jpg",
            "images/170x170/sword_poke.jpg",
            "images/170x170/scarlet_poke.jpg"
        };
        String[] labels = {
            "1世代", "2世代", "3世代", "4世代", "5世代", "6世代", "7世代", "8世代", "9世代"
        };

        // ボタンアクション
        ActionListener[] actions = {
            e -> showGenerationPanel1x2("images/300x300/red_poke.jpg", "images/300x300/green_poke.jpg", LocalDateTime.of(1996, 2, 27, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/gold_poke.jpg", "images/300x300/silver_poke.jpg", LocalDateTime.of(1999, 11, 21, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/ruby_poke.jpg", "images/300x300/sapphire_poke.jpg", LocalDateTime.of(2002, 11, 21, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/diamond_poke.jpg", "images/300x300/pearl_poke.jpg", LocalDateTime.of(2006, 9, 28, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/black_poke.jpg", "images/300x300/white_poke.jpg", LocalDateTime.of(2010, 9, 18, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/x_poke.jpg", "images/300x300/y_poke.jpg", LocalDateTime.of(2013, 10, 12, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/sun_poke.jpg", "images/300x300/moon_poke.jpg", LocalDateTime.of(2016, 11, 18, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/sword_poke.jpg", "images/300x300/shield_poke.jpg", LocalDateTime.of(2019, 11, 15, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/scarlet_poke.jpg", "images/300x300/violet_poke.jpg", LocalDateTime.of(2022, 11, 18, 0, 0))
        };

        // ボタン作成
        for (int i = 0; i < images.length; i++) {
            JButton button = createButton(labels[i], images[i], actions[i]);
            gPanel.add(button);
        }

        buttonS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sub();
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

        // 画像とラベルを設定
        String[] images = {
            "images/190x190/gb_poke.jpg",
            "images/190x190/coliseum_poke.jpg",
            "images/190x190/usun_poke.jpg",
            "images/190x190/a_legends_poke.jpg",
        };
        String[] labels = {
            "1990年代","2000年代","2010年代","2020年代"
        };

        // ボタンアクション
        ActionListener[] actions = {
            e -> g1sub(), e -> g2sub(), e -> g3sub(), e -> g4sub(),
        };

        // ボタン作成
        for (int i = 0; i < images.length; i++) {
            JButton button = createButton(labels[i], images[i], actions[i]);
            gPanel.add(button);
        }

        // ボタンクリックアクション
        buttonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main();
            }
        });

        // パネルをフレームに追加
        getContentPane().add(gPanel);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    private void showGenerationPanel1x2(String imagePath1, String imagePath2, LocalDateTime releaseDate) {
        // もろもろ関数
        menu();

        // 画像・時間パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));// 1x2のグリッドレイアウトを設定
        JPanel timePanel = new JPanel(new GridLayout(3, 1));// 3x1のグリッドレイアウトを設定

        // 画像を添付・パネルに追加
        JLabel label1 = new JLabel(new ImageIcon(imagePath1));
        JLabel label2 = new JLabel(new ImageIcon(imagePath2));
        imagePanel.add(label1);
        imagePanel.add(label2);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 時間変換ボタン関数
        conversion();

        // 発売日
        specificTime = releaseDate;
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差の表示の仕方関数
        difference();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel, BorderLayout.CENTER);
        getContentPane().add(timePanel, BorderLayout.SOUTH);

        // パネルを再検討
        revalidate();
        repaint();
    }

    private void showGenerationPanel1x1(String imagePath1,LocalDateTime releaseDate) {
        // もろもろ関数
        menu();

        // 画像・時間パネルを作成
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));// 1x1のグリッドレイアウトを設定
        JPanel timePanel = new JPanel(new GridLayout(3, 1));// 3x1のグリッドレイアウトを設定

        // 画像を添付・パネルに追加
        JLabel label1 = new JLabel(new ImageIcon(imagePath1));
        imagePanel.add(label1);

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 発売日と差のラベル関数
        difference_label();

        // 時間変換ボタン関数
        conversion();

        // 発売日
        specificTime = releaseDate;
        String specificTimeFormatted = specificTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        specificTimeLabel.setText("発売日: " + specificTimeFormatted);

        // 差の表示の仕方関数
        difference();

        // ラベルをパネルに追加
        timePanel.add(differenceLabel);
        timePanel.add(specificTimeLabel);
        timePanel.add(conversionButton);

        // パネルをフレームに追加
        getContentPane().add(imagePanel, BorderLayout.CENTER);
        getContentPane().add(timePanel, BorderLayout.SOUTH);

        // パネルを再検討
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

        // 画像とラベルを設定
        String[] images = {
            "images/190x190/blue_poke.jpg",
            "images/190x190/stadium_poke.jpg",
            "images/190x190/pika_poke.jpg",
            "images/190x190/gb_poke.jpg",
        };
        String[] labels = {
            "青", "スタジアム", "ピカチュウ", "GB"
        };

        // ボタンアクション
        ActionListener[] actions = {
            e -> showGenerationPanel1x1("images/300x300/blue_poke.jpg",LocalDateTime.of(1996, 10, 15, 0, 0, 0)), 
            e -> showGenerationPanel1x1("images/300x300/stadium_poke.jpg",LocalDateTime.of(1998, 8, 1, 0, 0, 0)), 
            e -> showGenerationPanel1x1("images/300x300/pika_poke.jpg",LocalDateTime.of(1998, 9, 12, 0, 0, 0)), 
            e -> showGenerationPanel1x1("images/300x300/gb_poke.jpg",LocalDateTime.of(1998, 12, 18, 0, 0, 0)), 
        };

        // ボタン作成
        for (int i = 0; i < images.length; i++) {
            JButton button = createButton(labels[i], images[i], actions[i]);
            gPanel.add(button);
        }

        // パネルをフレームに追加
        getContentPane().add(gPanel);

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

        // 画像とラベルを設定
        String[] images = {
            "images/120x120/crystal_poke.jpg",
            "images/120x120/gb2_poke.jpg",
            "images/120x120/coliseum_poke.jpg",
            "images/120x120/r_red_poke.jpg",
            "images/120x120/emerald_poke.jpg",
            "images/120x120/xd_poke.jpg",
            "images/120x120/wonder_red_poke.jpg",
            "images/120x120/ranger_poke.png",
            "images/120x120/wonder_time_poke.png",
            "images/120x120/platinum_poke.png",
            "images/120x120/wonder_sky_poke.jpg",
            "images/120x120/r_gold_poke.jpg"
        };

        String[] labels = {
            "クリスタル", "GB2", "コロシアム", "FRLG", "エメラルド", "XD", "ポケダン赤・青", "ポケモンレンジャー", "ポケダン時・闇","プラチナ","ポケダン空","HGSS"
        };

        // ボタンアクション
        ActionListener[] actions = {
            e -> showGenerationPanel1x1("images/300x300/crystal_poke.jpg",LocalDateTime.of(2000, 12, 14, 0, 0, 0)), 
            e -> showGenerationPanel1x1("images/300x300/gb2_poke.jpg",LocalDateTime.of(2001, 3, 28, 0, 0, 0)), 
            e -> showGenerationPanel1x1("images/300x300/coliseum_poke.jpg",LocalDateTime.of(2003, 11, 21, 0, 0, 0)), 
            e -> showGenerationPanel1x2("images/300x300/r_red_poke.jpg", "images/300x300/r_green_poke.jpg", LocalDateTime.of(2004, 1, 29, 0, 0)),
            e -> showGenerationPanel1x1("images/300x300/emerald_poke.jpg",LocalDateTime.of(2004, 9, 16, 0, 0, 0)),
            e -> showGenerationPanel1x1("images/300x300/xd_poke.jpg",LocalDateTime.of(2005, 8, 4, 0, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/wonder_blue_poke.jpg", "images/300x300/wonder_red_poke.jpg", LocalDateTime.of(2005, 11, 17, 0, 0)),
            e -> showGenerationPanel1x1("images/300x300/ranger_poke.png",LocalDateTime.of(2006, 3, 23, 0, 0, 0)), 
            e -> showGenerationPanel1x2("images/300x300/wonder_time_poke.png", "images/300x300/wonder_dark_poke.png", LocalDateTime.of(2007, 9, 13, 0, 0)),
            e -> showGenerationPanel1x1("images/300x300/platinum_poke.png",LocalDateTime.of(2008, 9, 13, 0, 0, 0)), 
            e -> showGenerationPanel1x1("images/300x300/wonder_sky_poke.jpg",LocalDateTime.of(2009, 4, 18, 0, 0, 0)), 
            e -> showGenerationPanel1x2("images/300x300/r_gold_poke.jpg", "images/300x300/r_silver_poke.jpg", LocalDateTime.of(2009, 9, 12, 0, 0))
        };

        // ボタン作成
        for (int i = 0; i < images.length; i++) {
            JButton button = createButton(labels[i], images[i], actions[i]);
            gPanel.add(button);
        }

        // パネルをフレームに追加
        getContentPane().add(gPanel);

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

        // 画像とラベルを設定
        String[] images = {
            "images/120x120/black2_poke.jpg",
            "images/120x120/r_ruby_poke.jpg",
            "images/120x120/wonder_hyper_poke.jpg",
            "images/120x120/vc_red_poke.png",
            "images/120x120/vc_gold_poke.png",
            "images/120x120/usun_poke.jpg",
            "images/120x120/vc_crystal_poke.png",
            "images/120x120/r_pika_poke.jpg"
        };
        String[] labels = {
            "BW2","ORAS","超ポケダン","VC赤緑青ピカ","VC金銀","USUM","VCクリスタル","ピカブイ"
        };

        // ボタンアクション
        ActionListener[] actions = {
            e -> showGenerationPanel1x2("images/300x300/black2_poke.jpg", "images/300x300/white2_poke.jpg", LocalDateTime.of(2012, 6, 23, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/r_ruby_poke.jpg", "images/300x300/r_sapphire_poke.jpg", LocalDateTime.of(2014, 11, 21, 0, 0)),
            e -> showGenerationPanel1x1("images/300x300/wonder_hyper_poke.jpg",LocalDateTime.of(2015, 9, 17, 0, 0, 0)),
            e -> vc1(),
            e -> showGenerationPanel1x2("images/300x300/vc_gold_poke.png", "images/300x300/vc_silver_poke.png", LocalDateTime.of(2017, 9, 22, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/usun_poke.jpg", "images/300x300/umoo_poke.jpg", LocalDateTime.of(2017, 11, 17, 0, 0)),
            e -> showGenerationPanel1x1("images/300x300/vc_crystal_poke.png",LocalDateTime.of(2018, 1, 26, 0, 0, 0)),
            e -> showGenerationPanel1x2("images/300x300/r_pika_poke.jpg", "images/300x300/r_eev.jpg", LocalDateTime.of(2018, 11, 16, 0, 0))
        };

        // ボタン作成
        for (int i = 0; i < images.length; i++) {
            JButton button = createButton(labels[i], images[i], actions[i]);
            gPanel.add(button);
        }

        // パネルをフレームに追加
        getContentPane().add(gPanel);

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

    private void g4sub(){
        // もろもろ関数
        menu();

        // 本編と外伝ボタンクリック時のアクション
        buttonMS();

        // 世代パネルを作成
        JPanel gPanel = new JPanel(new GridLayout(1, 2));// 1x2のグリッドレイアウトを設定

        // 画像とラベルを設定
        String[] images = {
            "images/100x160/r_diamond_poke.jpg",
            "images/100x160/a_legends_poke.jpg",
        };
        String[] labels = {
            "BDSP", "レジェンズアルセウス"
        };

        // ボタンアクション
        ActionListener[] actions = {
            e -> showGenerationPanel1x2("images/300x300/r_diamond_poke.jpg", "images/300x300/r_pearl_poke.jpg", LocalDateTime.of(2021, 11, 19, 0, 0)),
            e -> showGenerationPanel1x1("images/300x300/a_legends_poke.jpg",LocalDateTime.of(2022, 1, 28, 0, 0, 0))
        };

        // ボタン作成
        for (int i = 0; i < images.length; i++) {
            JButton button = createButton(labels[i], images[i], actions[i]);
            gPanel.add(button);
        }

        // パネルをフレームに追加
        getContentPane().add(gPanel);

        // レイアウトを再検討
        revalidate();
        repaint();
    }

    // 本編・外伝ボタン関数
    private JButton createButton(String label, String imagePath, ActionListener action) {
        ImageIcon icon = new ImageIcon(imagePath);
        JButton button = new JButton(label, icon);
        button.setFont(newFont);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.addActionListener(action);
        return button;
    }

    // 現在時刻を更新するメソッド
    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss                                   ");
        timeLabel.setText(now.format(formatter));
    }
}