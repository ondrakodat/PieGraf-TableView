1. Dodani datovych typu do deklerace objektu = private TableColumn<?,?> name; 
2. Vytvoreni listu pro Tridu Objektu(v tomto pripade Zamestnanci) = ObservableList<Zamestnanci> list = FXCollections.ObservableList();
	- rovnou nastavenime hodnoty pro TextView, to nastavujeme v initialize. =  tvInformace.setItems(list);
3. Prirazeni jaky objekt ma byt v danem sloupci zobrazen, to nastavujeme v initialize.
           //@FXML private TableColumn<Zamestnanci, String> jmeno;
        - jmeno.setCellValueFactory(new PropertyValueFactory<Zamestnanci, String>("jmeno"));
4. Udelame metodu pro aktualizovani pieGrafu, udělá se list pro data, for each loop pro vsechny objekty a nastaveni dat. 
	- private void updatePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();        
        for (User user : list) {
            pieChartData.add(new PieChart.Data(user.getName(), user.getAge()));
        }      
        pieChart.setData(pieChartData);
    }
5. A nyní metoda Pridej. 
		
	5.1 Vytvoreni Dialogu pro přidani hodnot do ListView a take grafu. - dialogy jsou pouzivany pro sber dat a proto jim davame typ. Timto rikame s jakymi daty budeme pracovat
		Dialog<Zamestnanci>  dialog = new Dialog();
   	        dialog.setContentText("Pridejte uzivatele");
		dialog.setHeaderText("Dialog pro zadani hodnot");

	5.2 Vytvoreni GridPanu a zadani rozmeru
		GridPane gridPane = new GridPane();
	        gridPane.setVgap(10);
		gridPane.setHgap(10);

	5.3 Vytvoreni textovych polí
		TextField tfJmeno = new TextField();
     		TextField tfPrijmeni = new TextField();
	        TextField tfPlat = new TextField();

	5.4 Pridani komponent do GridPanu, ktery jsme vytvorili drive. - prvni cislo jsou sloupce, to druhe radky.
	        gridPane.add(new Label("Jmeno"), 0, 0);
     	        gridPane.add(new Label("Prijmeni"), 0, 1);
  	        gridPane.add(new Label("Plat"), 0, 2);
	        gridPane.add(tfJmeno, 1, 0);
                gridPane.add(tfPrijmeni, 1, 0);
                gridPane.add(tfPlat, 1, 2);
	5.5 Nastaveni obsahu dialogoveho okna. 
			//vysvětleni metod
				1. getDialogPane() = vraci panel dialogu, to je misto kde jsou umisteny vsechny prvky dialogu napč. tlacitka, textove pole atd. 
				2. setContent(gridPane) = nastavi obsah panelu dialogu na atribut, ktery je v parametru metody. gridPane v tomto pripade je to kam jsme zadali vsechen obsah.  
		dialog.getDialogPane().setContent(gridPane);

	5.6 Pridani tlacitek 
		ButtonType btnOk = new ButtonType("Ok", ButtonData.OK_DONE);
                ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
   		dialog.getDialogPane().getButtonTypes().addAll(btnOk, btnCancel);

	5.7 Zpracovani vstupu po kliknuti na Pridej
	dialog.setResultConverter(new Callback<ButtonType, Zamestnanci>() {
           		 @Override
         		   public Zamestnanci call(ButtonType dialogButton) {
           		     if (dialogButton == btnOk) {
               		     String jmenoDialog = tfJmeno.getText().trim();
                             String prijmeniDialog = tfPrijmeni.getText().trim();
                             int platDialog = Integer.parseInt(tfPlat.getText().trim());
                             return new Zamestnanci(jmenoDialog, prijmeniDialog, platDialog);
                             }
                         return null;
                            }
                          });
	5.8 Zobrazeni okna
		Optional<Zamestnanci> result = dialog.showAndWait();
	
	5.9 Pridani noveho pracovnika do seznamu a zobrazeni v grafu
		 result.ifPresent(newUser -> {
      			  list.add(newUser);
   		 });
    			 updatePieChart();

			
        
