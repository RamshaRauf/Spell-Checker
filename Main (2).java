class Main {
  public static void main(String[] args) {
    SpellDictionary dict = new SpellDictionary("words.txt");
    

    //checks the nearMisses method
    //test Deletion
    TestCode.beginTest("Deletions");
    TestCode.subTest("mire",dict.nearMisses("mirre").contains("mire"));
    TestCode.subTest("happy",dict.nearMisses("happpy").contains("happy"));
    TestCode.subTest("school",dict.nearMisses("schoool").contains("school"));
    TestCode.subTest("echelons",dict.nearMisses("echelonrs").contains("echelons"));
    TestCode.concludeTest();

    //test Insertion
    TestCode.beginTest("Insertion");
    TestCode.subTest("quest",dict.nearMisses("qust").contains("quest"));
    TestCode.subTest("educationally",dict.nearMisses("educationaly").contains("educationally"));
    TestCode.subTest("elegant",dict.nearMisses("eleant").contains("elegant"));
    TestCode.subTest("make",dict.nearMisses("mae").contains("make"));
    TestCode.concludeTest();

    //test Substitution
    TestCode.beginTest("Substitutions");
    TestCode.subTest("yellow",dict.nearMisses("yelluw").contains("yellow"));
    TestCode.subTest("melon",dict.nearMisses("melun").contains("melon"));
    TestCode.subTest("elephant",dict.nearMisses("elepfant").contains("elephant"));
    TestCode.subTest("mark",dict.nearMisses("marc").contains("mark"));
    TestCode.concludeTest();

    //test transposition
    TestCode.beginTest("Transposition");
    TestCode.subTest("drinking",dict.nearMisses("drinkign").contains("drinking"));
    TestCode.subTest("cautious",dict.nearMisses("cautiuos").contains("cautious"));
    TestCode.subTest("eggplant",dict.nearMisses("eggpalnt").contains("eggplant"));
    TestCode.subTest("beach",dict.nearMisses("baech").contains("beach"));
    TestCode.concludeTest();

    //test splits 
    TestCode.beginTest("Splits");
    TestCode.subTest("fir re",dict.nearMisses("firre").contains("fir re"));
    TestCode.subTest("photocopy",dict.nearMisses("photocopy").contains("photo copy"));
    TestCode.subTest("eggplant",dict.nearMisses("eggplant").contains("egg plant"));
    TestCode.subTest("make kiss",dict.nearMisses("makekiss").contains("make kiss"));
    TestCode.concludeTest();

   
  }
}