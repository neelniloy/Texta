package com.sarker.texta;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AsciiV2 extends AppCompatActivity {

    private ArrayList<Item> mExampleList;

    private RecyclerView mRecyclerView;
    private ASCIIAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private InterstitialAd mInterstitialAd;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascii_v2);

        createExampleList();
        buildRecyclerView();

        MobileAds.initialize(this, initializationStatus -> {
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        handler = new Handler(Looper.getMainLooper());
        runnable = () -> InterstitialAd.load(this,"ca-app-pub-1276360114688784/9853001105", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
        handler.postDelayed(runnable, 15*1000L);




    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new Item("", "( ͡° ͜ʖ ͡°)"));
        mExampleList.add(new Item("", "ʕ⁎̯͡⁎ʔ"));
        mExampleList.add(new Item("Angry", "ಠ_ಠ"));
        mExampleList.add(new Item("Cool", "( •_•)\n" +
                "( •_•)>⌐■-■\n" +
                "(⌐■_■)"));
        mExampleList.add(new Item("", "(\\____/)\n" +
                "( ͡ ͡° ͜ ʖ ͡ ͡°)\n" +
                "\\╭☞ \\╭☞"));
        mExampleList.add(new Item("Minion bear", "ʕ•ᴥ•ʔ"));
        mExampleList.add(new Item("", "•ᴗ•"));
        mExampleList.add(new Item("", "(◉ܫ◉)"));
        mExampleList.add(new Item("", "(^‿^)"));
        mExampleList.add(new Item("", "(စ‿စ )"));
        mExampleList.add(new Item("", "( ဖ‿ဖ)"));
        mExampleList.add(new Item("", "(⨪ ิ ⨪)"));
        mExampleList.add(new Item("", "(ↈ_ↈ)"));
        mExampleList.add(new Item("", "(;︵;)"));
        mExampleList.add(new Item("", "(ఠ\uD802\uDD03ఠ)"));
        mExampleList.add(new Item("Lovely face with eyes in heart", "(♥‿♥)"));
        mExampleList.add(new Item("", "{\\__/}\n" +
                "(●_●)\n" +
                "( >\uD83C\uDF2E "));
        mExampleList.add(new Item("Shrug", "¯\\_(ツ)_/¯"));
        mExampleList.add(new Item("Cat", "ฅ^•ﻌ•^ฅ"));
        mExampleList.add(new Item("Confused", "(⊙_☉)"));
//        mExampleList.add(new Item("F###", "....................../´¯/)\n" +
//                "....................,/¯../\n" +
//                ".................../..../\n" +
//                "............./´¯/'...'/´¯¯`·¸\n" +
//                "........../'/.../..../......./¨¯\\\n" +
//                "........('(...´...´.... ¯~/'...')\n" +
//                ".........\\.................'...../\n" +
//                "..........''...\\.......... _.·´\n" +
//                "............\\..............(\n" +
//                "..............\\.............\\..."));
        mExampleList.add(new Item("", "/\\_/\\\n" +
                "(='_' )\n" +
                "(, (\") (\")"));
        mExampleList.add(new Item("K!ssing", "(っ˘з(˘⌣˘ )"));
        mExampleList.add(new Item("Dancing", "(~‾▿‾)~"));
        mExampleList.add(new Item("Giving Up", "o(╥﹏╥)o"));
        mExampleList.add(new Item("Line 1", "⊂_ヽ\n" +
                "　 ＼＼     \n" +
                "　　 ＼( ͡° ͜ʖ ͡°)\n" +
                "　　　 >　⌒ヽ\n" +
                "　　　/ 　 へ＼\n" +
                "　　 /　　/　＼    \n" +
                "　　 ﾚ　ノ　　 ヽ_つ\n" +
                "　　/　/\n" +
                "　 /　/|\n" +
                "　(　(ヽ\n" +
                "　|　|、\n" +
                "　| 丿 ＼ ⌒)\n" +
                "　| |　　) /\n" +
                "ノ )　　Lﾉ\n" +
                "(_／"));
        mExampleList.add(new Item("Zombie", "[¬º-°]¬"));
        mExampleList.add(new Item("Cry", "(╥_╥)"));
        mExampleList.add(new Item("Bear", "✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧"));
        mExampleList.add(new Item("Cry 2", ".·´¯`(>▂<)´¯`·."));
        mExampleList.add(new Item("Angry 2", "ᕙ(⇀‸↼‶)ᕗ"));
        mExampleList.add(new Item("", "/﹋\\\n" +
                "(҂`_´)\n" +
                "<,︻╦╤─ ҉\n" +
                "/﹋\\"));
        mExampleList.add(new Item("", "/\\_/\\\n" +
                "(=' ᴗ' )\n" +
                "(, (\") (\")"));
        mExampleList.add(new Item("Cry 3", "(´ ͡༎ຶ ͜ʖ ͡༎ຶ )"));
        mExampleList.add(new Item("Smart Thinking", "f(ಠ‿↼)z"));
        mExampleList.add(new Item("Happy", "｡^‿^｡"));
        mExampleList.add(new Item("", "(●__●)"));
        mExampleList.add(new Item("Bears", "ʕ•̫͡•ʕ•̫͡•ʔ•̫͡•ʔ"));
        mExampleList.add(new Item("Sleeping", "(ᴗ˳ᴗ)"));
        mExampleList.add(new Item("Music", "───────────────⚪────\n" +
                "◄◄⠀▐▐ ⠀►►⠀⠀ ⠀ 1:17 / 3:48 ⠀ ───○ \uD83D\uDD0A⠀ ᴴᴰ ⚙ ❐ ⊏⊐"));
        mExampleList.add(new Item("Hey baby", "(☞⌐■_■)☞"));
        mExampleList.add(new Item("", " ̿̿ ̿̿ ̿'̿'\\̵͇̿̿\\з= ( ▀ ͜͞ʖ▀) =ε/̵͇̿̿/’̿’̿ ̿ ̿̿ ̿̿ ̿̿"));
        mExampleList.add(new Item("Angry 3", "(◣_◢)"));
        mExampleList.add(new Item("", "( •_•)"));
        mExampleList.add(new Item("Cat 4", "(=^･ｪ･^="));
        mExampleList.add(new Item("Bat", "(/╰^._.^╯\\)"));
        mExampleList.add(new Item("Cat 4", "(⁎˃ᆺ˂)"));
        mExampleList.add(new Item("Victory", "☆┌─┐　─┐☆\n" +
                "　│▒│ /▒/\n" +
                "　│▒│/▒/ ¡\n" +
                "　│▒ /▒/─┬─┐\n" +
                "　│▒│▒|▒│▒│\n" +
                "┌┴─┴─┐-┘─\n" +
                "│▒┌──┘▒▒▒\n" +
                "└┐▒▒▒▒▒▒┌┘\n" +
                "　└┐▒▒▒▒┌"));
        mExampleList.add(new Item("Cat 6", "(=｀ェ´=)"));
        mExampleList.add(new Item("Afraid", "(ㆆ _ ㆆ)"));
        mExampleList.add(new Item("Angel", "☜(⌒▽⌒)☞"));
        mExampleList.add(new Item("Angry 4", "•`_´•"));
//        mExampleList.add(new Item("A#s", "(‿|‿)"));
        mExampleList.add(new Item("Blackeye", "0__#"));
        mExampleList.add(new Item("Blush", "(˵ ͡° ͜ʖ ͡°˵)"));
        mExampleList.add(new Item("Bond 007", "┌( ͝° ͜ʖ͡°)=ε/̵͇̿̿/’̿’̿ ̿"));
        mExampleList.add(new Item("Butterfly", "ƸӜƷ"));
        mExampleList.add(new Item("Cat 7", "(= ФェФ=)"));
        mExampleList.add(new Item("Cute", "(｡◕‿‿◕｡)"));
        mExampleList.add(new Item("Dab", "ヽ( •_)ᕗ"));
        mExampleList.add(new Item("Depressed", "(︶︹︶)"));
//        mExampleList.add(new Item("F##", "┌П┐(ಠ_ಠ)"));
        mExampleList.add(new Item("Gun", "︻╦╤─"));
        mExampleList.add(new Item("Oh Shit", "( º﹃º )"));
        mExampleList.add(new Item("Rose", "✿ڿڰۣ—"));
        mExampleList.add(new Item("Shy", "=^_^="));
        mExampleList.add(new Item("Surrender", "\\_(-_-)_/"));
        mExampleList.add(new Item("Swag", "(̿▀̿‿ ̿▀̿ ̿)"));
        mExampleList.add(new Item("Thanks", "\\(^-^)/"));
        mExampleList.add(new Item("WTFun", "(⊙＿⊙')"));
        mExampleList.add(new Item("No Worry", "_へ__(‾◡◝ )>"));
        mExampleList.add(new Item("", "(　◠ ◡ ◠　)"));
//        mExampleList.add(new Item("F###", "( ︶︿︶)_╭∩╮"));
//        mExampleList.add(new Item("F####", "╭∩╮(︶︿︶)╭∩╮"));
        mExampleList.add(new Item("", "(҂⌣̀_⌣́)"));
        mExampleList.add(new Item("Waving", "(¬_¬)ﾉ"));
        mExampleList.add(new Item("Sad", "(︶︹︺)"));
        mExampleList.add(new Item("Cat 8", "₍⸍⸌̣ʷ̣̫⸍̣⸌₎"));
        mExampleList.add(new Item("Cat 9", " _._     _,-'\"\"`-._\n" +
                "(,-.`._,'(       |\\`-/|\n" +
                "    `-.-' \\ )-`( , o o)\n" +
                "          `-    \\`_`\"'-"));
        mExampleList.add(new Item("Cat 12", "  ,-.       _,---._ __  / \\\n" +
                " /  )    .-'       `./ /   \\\n" +
                "(  (   ,'            `/    /|\n" +
                " \\  `-\"             \\'\\   / |\n" +
                "  `.              ,  \\ \\ /  |\n" +
                "   /`.          ,'-`----Y   |\n" +
                "  (            ;        |   '\n" +
                "  |  ,-.    ,-'         |  /\n" +
                "  |  | (   |            | /\n" +
                "  )  |  \\  `.___________|/\n" +
                "  `--'   `--'"));
        mExampleList.add(new Item("Cat 13", "        .__....._             _.....__,\n" +
                "            .\": o :':         ;': o :\".\n" +
                "            `. `-' .'.       .'. `-' .'\n" +
                "              `---'             `---'\n" +
                "\n" +
                "    _...----...      ...   ...      ...----..._\n" +
                " .-'__..-\"\"'----    `.  `\"`  .'    ----'\"\"-..__`-.\n" +
                "'.-'   _.--\"\"\"'       `-._.-'       '\"\"\"--._   `-.`\n" +
                "'  .-\"'                  :                  `\"-.  `\n" +
                "  '   `.              _.'\"'._              .'   `\n" +
                "        `.       ,.-'\"       \"'-.,       .'\n" +
                "          `.                           .'\n" +
                "            `-._                   _.-'\n" +
                "                `\"'--...___...--'\"`"));
        mExampleList.add(new Item("Cat 14", " _                        \n" +
                "       \\`*-.                    \n" +
                "        )  _`-.                 \n" +
                "       .  : `. .                \n" +
                "       : _   '  \\               \n" +
                "       ; *` _.   `*-._          \n" +
                "       `-.-'          `-.       \n" +
                "         ;       `       `.     \n" +
                "         :.       .        \\    \n" +
                "         . \\  .   :   .-'   .   \n" +
                "         '  `+.;  ;  '      :   \n" +
                "         :  '  |    ;       ;-. \n" +
                "         ; '   : :`-:     _.`* ;\n" +
                "      .*' /  .*' ; .*`- +'  `*' \n" +
                "      `*-*   `*-*  `*-*'"));
        mExampleList.add(new Item("Lion King", "              ,   __, ,\n" +
                "   _.._         )\\/(,-' (-' `.__\n" +
                "  /_   `-.      )'_      ` _  (_    _.---._\n" +
                " // \\     `-. ,'   `-.    _\\`.  `.,'   ,--.\\\n" +
                "// -.\\       `        `.  \\`.   `/   ,'   ||\n" +
                "|| _ `\\_         ___    )  )     \\  /,-'  ||\n" +
                "||  `---\\      ,'__ \\   `,' ,--.  \\/---. //\n" +
                " \\\\  .---`.   / /  | |      |,-.\\ |`-._ //\n" +
                "  `..___.'|   \\ |,-| |      |_  )||\\___//\n" +
                "    `.____/    \\\\\\O| |      \\o)// |____/\n" +
                "         /      `---/        \\-'  \\\n" +
                "         |        ,'|,--._.--')    \\\n" +
                "         \\       /   `n     n'\\    /\n" +
                "          `.   `<   .::`-,-'::.) ,'       \n" +
                "            `.   \\-.____,^.   /,'\n" +
                "              `. ;`.,-V-.-.`v'\n" +
                "                \\| \\     ` \\|\\\n" +
                "                 ;  `-^---^-'/\n" +
                "                  `-.______,'"));
        mExampleList.add(new Item("Cat 15", ",\n" +
                "       \\`-._           __\n" +
                "        \\\\  `-..____,.'  `.\n" +
                "         :`.         /    \\`.\n" +
                "         :  )       :      : \\\n" +
                "          ;'        '   ;  |  :\n" +
                "          )..      .. .:.`.;  :\n" +
                "         /::...  .:::...   ` ;\n" +
                "         ; _ '    __        /:\\\n" +
                "         `:o>   /\\o_>      ;:. `.\n" +
                "        `-`.__ ;   __..--- /:.   \\\n" +
                "        === \\_/   ;=====_.':.     ;\n" +
                "         ,/'`--'...`--....        ;\n" +
                "              ;                    ;\n" +
                "            .'                      ;\n" +
                "          .'                        ;\n" +
                "        .'     ..     ,      .       ;\n" +
                "       :       ::..  /      ;::.     |\n" +
                "      /      `.;::.  |       ;:..    ;\n" +
                "     :         |:.   :       ;:.    ;\n" +
                "     :         ::     ;:..   |.    ;\n" +
                "      :       :;      :::....|     |\n" +
                "      /\\     ,/ \\      ;:::::;     ;\n" +
                "    .:. \\:..|    :     ; '.--|     ;\n" +
                "   ::.  :''  `-.,,;     ;'   ;     ;\n" +
                ".-'. _.'\\      / `;      \\,__:      \\\n" +
                "`---'    `----'   ;      /    \\,.,,,/\n" +
                "                   `----`                 "));
        mExampleList.add(new Item("Lion", "         ,.\n" +
                "                  ,_> `.   ,';\n" +
                "              ,-`'      `'   '`'._\n" +
                "           ,,-) ---._   |   .---''`-),.\n" +
                "         ,'      `.  \\  ;  /   _,'     `,\n" +
                "      ,--' ____       \\   '  ,'    ___  `-,\n" +
                "     _>   /--. `-.              .-'.--\\   \\__\n" +
                "    '-,  (    `.  `.,`~ \\~'-. ,' ,'    )    _\\\n" +
                "    _<    \\     \\ ,'  ') )   `. /     /    <,.\n" +
                " ,-'   _,  \\    ,'    ( /      `.    /        `-,\n" +
                " `-.,-'     `.,'       `         `.,'  `\\    ,-'\n" +
                "  ,'       _  /   ,,,      ,,,     \\     `-. `-._\n" +
                " /-,     ,'  ;   ' _ \\    / _ `     ; `.     `(`-\\\n" +
                "  /-,        ;    (o)      (o)      ;          `'`,\n" +
                ",~-'  ,-'    \\     '        `      /     \\      <_\n" +
                "/-. ,'        \\                   /       \\     ,-'\n" +
                "  '`,     ,'   `-/             \\-' `.      `-. <\n" +
                "   /_    /      /   (_     _)   \\    \\          `,\n" +
                "     `-._;  ,' |  .::.`-.-' :..  |       `-.    _\\\n" +
                "       _/       \\  `:: ,^. :.:' / `.        \\,-'\n" +
                "     '`.   ,-'  /`-..-'-.-`-..-'\\            `-.\n" +
                "       >_ /     ;  (\\/( ' )\\/)  ;     `-.    _<\n" +
                "       ,-'      `.  \\`-^^^-'/  ,'        \\ _<\n" +
                "        `-,  ,'   `. `\"\"\"\"\"' ,'   `-.   <`'\n" +
                "          ')        `._.,,_.'        \\ ,-'\n" +
                "           '._        '`'`'   \\       <\n" +
                "              >   ,'       ,   `-.   <`'\n" +
                "               `,/          \\      ,-`\n" +
                "                `,   ,' |   /     /\n" +
                "                 '; /   ;        (\n" +
                "                  _)|   `       (\n" +
                "                  `')         .-'\n" +
                "                    <_   \\   /    hjw\n" +
                "                      \\   /\\(\n" +
                "                       `;/  `  c"));
        mExampleList.add(new Item("Cat 16", "    ._\n" +
                "              .-'  `-.\n" +
                "           .-'        \\\n" +
                "          ;    .-'\\    ;\n" +
                "          `._.'    ;   |\n" +
                "                   |   |\n" +
                "                   ;   :\n" +
                "                  ;   :\n" +
                "                  ;   :\n" +
                "                 /   /\n" +
                "                ;   :                   ,\n" +
                "                ;   |               .-\"7|\n" +
                "              .-'\"  :            .-' .' :\n" +
                "           .-'       \\         .'  .'   `.\n" +
                "         .'           `-. \"\"-.-'`\"\"    `\",`-._..--\"7\n" +
                "         ;    .          `-.J `-,    ;\"`.;|,_,    ;\n" +
                "       _.'    |         `\"\" `. .\"\"\"--. o \\:.-. _.'\n" +
                "    .\"\"       :            ,--`;   ,  `--/}o,' ;\n" +
                "    ;   .___.'        /     ,--.`-. `-..7_.-  /_\n" +
                "     \\   :   `..__.._;    .'__;    `---..__.-'-.`\"-,\n" +
                "     .'   `--. |   \\_;    \\'   `-._.-\")     \\\\  `-,\n" +
                "     `.   -.`_):      `.   `-\"\"\"`.   ;__.' ;/ ;   \"\n" +
                "       `-.__7\"  `-..._.'`7     -._;'  ``\"-''\n" +
                "                         `--.,__.'                 "));
        mExampleList.add(new Item("Cat 17", "  ,\n" +
                "                 \\)\\_\n" +
                "                /    '. .---._\n" +
                "              =P ^     `      '.\n" +
                "               `--.       /     \\\n" +
                "               .-'(       \\      |\n" +
                "              (.-'   )-..__>   , ;\n" +
                "              (_.--``    (__.-/ /\n" +
                "                      .-.__.-'.'\n" +
                "                       '-...-'"));
        mExampleList.add(new Item("Spider", "           ;               ,           \n" +
                "         ,;                 '.         \n" +
                "        ;:                   :;         \n" +
                "       ::                     ::       \n" +
                "       ::                     ::       \n" +
                "       ':                     :         \n" +
                "        :.                    :         \n" +
                "     ;' ::                   ::  '     \n" +
                "    .'  ';                   ;'  '.     \n" +
                "   ::    :;                 ;:    ::   \n" +
                "   ;      :;.             ,;:     ::   \n" +
                "   :;      :;:           ,;\"      ::   \n" +
                "   ::.      ':;  ..,.;  ;:'     ,.;:   \n" +
                "    \"'\"...   '::,::::: ;:   .;.;\"\"'     \n" +
                "        '\"\"\"....;:::::;,;.;\"\"\"         \n" +
                "    .:::.....'\"':::::::'\",...;::::;.   \n" +
                "   ;:' '\"\"'\"\";.,;:::::;.'\"\"\"\"\"\"  ':;   \n" +
                "  ::'         ;::;:::;::..         :;   \n" +
                " ::         ,;:::::::::::;:..       :: \n" +
                " ;'     ,;;:;::::::::::::::;\";..    ':. \n" +
                "::     ;:\"  ::::::\"\"\"'::::::  \":     :: \n" +
                " :.    ::   ::::::;  :::::::   :     ; \n" +
                "  ;    ::   :::::::  :::::::   :    ;   \n" +
                "   '   ::   ::::::....:::::'  ,:   '   \n" +
                "    '  ::    :::::::::::::\"   ::       \n" +
                "       ::     ':::::::::\"'    ::       \n" +
                "       ':       \"\"\"\"\"\"\"'      ::       \n" +
                "        ::                   ;:         \n" +
                "        ':;                 ;:\"         \n" +
                "          ';              ,;'           \n" +
                "            \"'           '\"             \n" +
                "              ' \n"));
        mExampleList.add(new Item("Frog", ":(¦)"));
        mExampleList.add(new Item("Frog 2", "        /00\\\n" +
                "   _/^(----)^\\_\n" +
                "^^^^^^^^^^^^^^"));
        mExampleList.add(new Item("", "        00         \n" +
                "      (\\__/)       \n" +
                "__(  I I   I I  )__"));
        mExampleList.add(new Item("", "     00 \n" +
                "     (--)\n" +
                "    ( || )\n" +
                "  ^^~~^^"));
        mExampleList.add(new Item("", "  @..@\n" +
                "   (----)\n" +
                " ( >__< )\n" +
                "^^ ~~ ^^"));
        mExampleList.add(new Item("", "      _    _\n" +
                "     (o)--(o)\n" +
                "    /.______.\\\n" +
                "   \\________/\n" +
                "    ./        \\.\n" +
                "   ( .        , )\n" +
                "   \\ \\_\\\\//_/ /\n" +
                "  ~~  ~~  ~~"));
        mExampleList.add(new Item("", "\uD80C\uDC00"));
        mExampleList.add(new Item("", "\uD80C\uDC2C"));
        mExampleList.add(new Item("", "\uD80C\uDC43 "));
        mExampleList.add(new Item("", "\uD80C\uDCD3"));
        mExampleList.add(new Item("", "\uD80C\uDD9A"));
        mExampleList.add(new Item("", "\uD80C\uDD8F"));
        mExampleList.add(new Item("Crazy Face", "ミ●﹏☉ミ"));
        mExampleList.add(new Item("Kiss", "( ˘ ³˘)♥"));
        mExampleList.add(new Item("Injured Life", "(҂◡_◡)"));
        mExampleList.add(new Item("Tired Face", "( ͡ಠ ʖ̯ ͡ಠ)"));
        mExampleList.add(new Item("Looking Down", "(._.)"));
        mExampleList.add(new Item("Robot Face", "{•̃_•̃}"));
        mExampleList.add(new Item("Minion face with big eyes", "(｡◕‿◕｡)"));
        mExampleList.add(new Item("Confused Face", "¿ⓧ_ⓧﮌ"));
        mExampleList.add(new Item("Line 1", "(° ͜ʖ͡°)╭∩╮"));
        mExampleList.add(new Item("Monkey Smile", "ʕʘ̅͜ʘ̅ʔ"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ASCIIAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onBackPressed() {

        if (mInterstitialAd != null) {
            mInterstitialAd.show(AsciiV2.this);
            mInterstitialAd = null;
            super.onBackPressed();
            handler.removeCallbacks(runnable);
        } else {
            super.onBackPressed();
            handler.removeCallbacks(runnable);
        }
    }
}
