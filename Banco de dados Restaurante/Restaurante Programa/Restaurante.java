import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 


public class Restaurante extends JFrame implements ActionListener{ 
    boolean consultado = false;

    //parte de bd
    Connection con;
    Statement stmt;
    
    //criando a Parte grafica
    JFrame insereFrame = new JFrame("Inserir"); //Janela de inserir
        //Comanda (código, data, status, mesa_num, ctps_anfitriao, comand_prod)
        JPanel comandaPanel = new JPanel(new GridLayout(20,1));
        JLabel codigoComandaLabel = new JLabel("CODIGO DA COMANDA");
        JTextField codigoComandaText = new JTextField(30);
        JLabel dataComandLabel = new JLabel("DATA(dia, mes, ano, horas, minutos)");                           
        JTextField dataComandaText = new JTextField();            
        JLabel statusComandalLabel = new JLabel("STATUS");
        Choice statusComandalChoice = new Choice();
        JLabel mesaComandaLabel = new JLabel("NUMERO DA MESA");                           
        JTextField mesaComandaText = new JTextField();    
        JLabel funcComandaLabel = new JLabel("CTPS DO FUNCIONARIO");                           
        JTextField funcComandText = new JTextField();
        JLabel comandProdLabel = new JLabel("COMAND_PROD");                           
        JTextField comandProdText = new JTextField();
        JButton colocarComanda = new JButton("Inserir");
        JButton atualizarComanda = new JButton("Atualizar");
        //MESA(Numero,Numero de Cadeiras, Localização, Disponibilidade)
        JPanel mesaPanel = new JPanel(new GridLayout(20,1));
        JLabel numeroMesaLabel= new JLabel("NUMERO DA MESA");           //NUMERO
        JTextField numeroMesaField= new JTextField(30);
        JLabel cadeiras = new JLabel("QUANTIDADE DE CADEIRAS");                 //QUATIDADE DE CADEIRA          
        JTextField cadeirasText = new JTextField();
        JLabel loc = new JLabel("LOCALIZAÇÃO");                           //LOC DA MESA
        Choice locChoice = new Choice();          
        JLabel disponibilidade = new JLabel("DIPONIBILIDADE");                         //DISPONIBILIDADE DA MESA  
        Choice disponibilidadeChoice = new Choice();
        JButton colocarMesa = new JButton("Inserir");
        JButton atualizarMesa = new JButton("Atualizar");
        //funcionário (ctps, nome, sexo, salario, turno, endereço, cargo, data_nascimento, data_admissao)
        JPanel funcPanel = new JPanel(new GridLayout(20,1));
        JLabel ctpsLabel= new JLabel("CTPS");       
        JTextField ctpsField= new JTextField(30);
        JLabel nomeFuncLabel = new JLabel("NOME");                           
        JTextField nomeFuncText = new JTextField();
        JLabel sexoFuncLabel = new JLabel("SEXO");                           
        Choice sexoFuncChoice = new Choice();
        JLabel salario = new JLabel("SALARIO");                           
        JTextField salarioText = new JTextField();
        JLabel turno = new JLabel("TURNO");                           
        Choice turnoChoice = new Choice();
        JLabel enderecoFuncLabel = new JLabel("ENDERECO");
        JTextField enderecoFuncText = new JTextField();
        JLabel cargoFuncLabel = new JLabel("CARGO");                           
        Choice cargoFuncChoice = new Choice();
        JLabel dataNascFuncLabel = new JLabel("DATA DE NASCIMENTO");                           
        JTextField dataNascFuncText = new JTextField(); 
        JLabel dataAdmFuncLabel = new JLabel("DATA DE ADMISSAO");                           
        JTextField dataAdmFuncText = new JTextField(); 
        JButton colocarFunc = new JButton("Inserir");
        JButton atualizarFunc = new JButton("Atualizar");
        //pedido (código, quantidade, prod_cod)
        JPanel pedidoPanel = new JPanel(new GridLayout(20,1));
        JLabel codPedidoLabel= new JLabel("CODIGO DO PEDIDO");  
        JTextField codPedidoField= new JTextField(30);
        JLabel quantidadePedido = new JLabel("QUANTIDADE");
        JTextField quantidadePedidoText= new JTextField(30);
        JLabel pedidoCodProdLabel= new JLabel("CODIGO DO PRODUTO");  
        JTextField pedidoCodProdField= new JTextField(30);
        JLabel pedidoCodComandaLabel = new JLabel("CODIGO DA COMANDA");
        JTextField pedidoCodComandaField= new JTextField(30);
        JButton colocarPedido = new JButton("Inserir");
        JButton atualizarPedido = new JButton("Atualizar");
        //produto (código, nome, preco, tipo)
        JPanel prodPanel = new JPanel(new GridLayout(20,1));
        JLabel codProdLabel= new JLabel("CODIGO DO PRODUTO");       
        JTextField codProdField= new JTextField(30);
        JLabel nomeProd = new JLabel("NOME DO PRATO");                           
        JTextField nomeProdText = new JTextField();
        JLabel precoProd = new JLabel("PREÇO");                           
        JTextField precoProdText = new JTextField();          
        JLabel tipoProd = new JLabel("TIPO");                           
        Choice tipoProdChoice = new Choice();
        JButton colocarProd = new JButton("Inserir");
        JButton atualizarProd = new JButton("Atualizar");
        void insere(){
            if(locChoice.getItemCount()>=3){        //se ja foi aberta             
                insereFrame.setVisible(true);
                return;
            }     
            colocarMesa.addActionListener(inserirListener);
            colocarComanda.addActionListener(inserirListener);
            colocarFunc.addActionListener(inserirListener);
            colocarPedido.addActionListener(inserirListener);
            colocarProd.addActionListener(inserirListener);
            atualizarComanda.addActionListener(atualizarListener);
            atualizarFunc.addActionListener(atualizarListener);
            atualizarMesa.addActionListener(atualizarListener);
            atualizarPedido.addActionListener(atualizarListener);
            atualizarProd.addActionListener(atualizarListener);
            insereFrame.setLayout(new GridLayout(1,5));
            //Comanda
            comandaPanel.add(codigoComandaLabel);
            comandaPanel.add(codigoComandaText);
            comandaPanel.add(dataComandLabel);
            comandaPanel.add(dataComandaText);
            comandaPanel.add(statusComandalLabel);
            statusComandalChoice.add("Aberta");
            statusComandalChoice.add("Fechada");
            comandaPanel.add(statusComandalChoice);  
            comandaPanel.add(mesaComandaLabel);                  
            comandaPanel.add(mesaComandaText);   
            comandaPanel.add(funcComandaLabel);                       
            comandaPanel.add(funcComandText);
            comandaPanel.add(comandProdLabel);                       
            comandaPanel.add(comandProdText);
            comandaPanel.add(colocarComanda);
            comandaPanel.add(atualizarComanda);
            comandaPanel.setBorder(BorderFactory.createTitledBorder("Comanda"));
            insereFrame.add(comandaPanel);
            //mesa
            mesaPanel.add(numeroMesaLabel);
            mesaPanel.add(numeroMesaField);
            mesaPanel.add(cadeiras);
            mesaPanel.add(cadeirasText);
            mesaPanel.add(loc);
            locChoice.add("Proximo a Porta");
            locChoice.add("Proximo ao Palco");
            mesaPanel.add(locChoice);
            mesaPanel.add(disponibilidade);
            disponibilidadeChoice.add("Disponivel");
            disponibilidadeChoice.add("Indisponivel");
            mesaPanel.add(disponibilidadeChoice);
            mesaPanel.add(colocarMesa);
            mesaPanel.add(atualizarMesa);
            mesaPanel.setBorder(BorderFactory.createTitledBorder("MESA"));
            insereFrame.add(mesaPanel);    
            //funcionario
            funcPanel.add(ctpsLabel);
            funcPanel.add(ctpsField);
            funcPanel.add(nomeFuncLabel);
            funcPanel.add(nomeFuncText);
            funcPanel.add(sexoFuncLabel);
            sexoFuncChoice.add("Masculino");
            sexoFuncChoice.add("Feminio");
            sexoFuncChoice.add("Outro");
            funcPanel.add(sexoFuncChoice);
            funcPanel.add(salario);
            funcPanel.add(salarioText);
            funcPanel.add(turno);
            turnoChoice.add("Integral");
            turnoChoice.add("Noite");
            funcPanel.add(turnoChoice);        
            funcPanel.add(enderecoFuncLabel);
            funcPanel.add(enderecoFuncText);                         
            funcPanel.add(cargoFuncLabel);
            cargoFuncChoice.add("Garçom");
            cargoFuncChoice.add("Gerente");
            funcPanel.add(cargoFuncChoice);                         
            funcPanel.add(dataNascFuncLabel);
            funcPanel.add(dataNascFuncText);                        
            funcPanel.add(dataAdmFuncLabel);
            funcPanel.add(dataAdmFuncText);
            funcPanel.add(colocarFunc);
            funcPanel.add(atualizarFunc);
            funcPanel.setBorder(BorderFactory.createTitledBorder("FUNCIONARIO"));
            insereFrame.add(funcPanel);
            //Pedido
            pedidoPanel.add(codPedidoLabel);
            pedidoPanel.add(codPedidoField);
            pedidoPanel.add(quantidadePedido);
            pedidoPanel.add(quantidadePedidoText);
            pedidoPanel.add(pedidoCodProdLabel);
            pedidoPanel.add(pedidoCodProdField);
            pedidoPanel.add(pedidoCodComandaLabel);
            pedidoPanel.add(pedidoCodComandaField);
            pedidoPanel.add(colocarPedido);
            pedidoPanel.add(atualizarPedido);
            pedidoPanel.setBorder(BorderFactory.createTitledBorder("PEDIDO"));
            insereFrame.add(pedidoPanel);
            //Produto
            prodPanel.add(codProdLabel);
            prodPanel.add(codProdField);
            prodPanel.add(nomeProd);
            prodPanel.add(nomeProdText);
            prodPanel.add(precoProd);
            prodPanel.add(precoProdText);
            prodPanel.add(tipoProd);
            prodPanel.add(tipoProdChoice);
            prodPanel.add(colocarProd);
            prodPanel.add(atualizarProd);
            tipoProdChoice.add("Entrada");
            tipoProdChoice.add("Prato principal");
            tipoProdChoice.add("Sobremesa");
            prodPanel.setBorder(BorderFactory.createTitledBorder("PRODUTO"));
            insereFrame.add(prodPanel);
            //fim
            insereFrame.pack();
            insereFrame.setVisible(true);
            insereFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }

    JFrame consultaFrame = new JFrame("Consulta"); //Janela de consultar
    JPanel consultaFramePanel = new JPanel(new GridLayout(8,1));
            JLabel consultarEmLabel = new JLabel("Consultar em: ");
            Choice consultarEmChoice = new Choice();
            JLabel consultarPorLabel = new JLabel("Por: ");
            Choice consultarPorChoice = new Choice();
            JTextField campoConsultaText = new JTextField();
            JButton procurarButton = new JButton("Consultar");
            JButton excluirButton = new JButton("Excluir");
            JTextArea resultadoConsulta = new JTextArea(100,100);
            JScrollPane resultadoConsultaScrollPane = new JScrollPane(resultadoConsulta);
        void consulta(){
            if(consultado){
                consultaFrame.setVisible(true);
                return;
            }
            consultaFrame.setLayout(new GridLayout(1,2));
            consultaFrame.setPreferredSize(new Dimension(700,700));
            consultado=true;

            consultaFramePanel.add(consultarEmLabel);
            consultaFramePanel.add(consultarEmChoice);
            consultaFramePanel.add(consultarPorLabel);
            consultaFramePanel.add(consultarPorChoice);
            consultaFramePanel.add(campoConsultaText);
            consultaFramePanel.add(procurarButton);
            procurarButton.addActionListener(consultarListener);
            consultaFramePanel.add(excluirButton);
            excluirButton.addActionListener(excluirListener);
            consultaFramePanel.setBorder(BorderFactory.createTitledBorder("CONSULTA"));
            consultaFrame.add(consultaFramePanel);
            consultaFrame.add(resultadoConsultaScrollPane);
            resultadoConsulta.setEditable(false);
            consultarEmChoice.add("COMANDA");
            consultarEmChoice.add("MESA");
            consultarEmChoice.add("FUNCIONARIO");
            consultarEmChoice.add("PEDIDO");
            consultarEmChoice.add("PRODUTO");
            consultarPorChoice.add("CODIGO");
            consultarPorChoice.add("DATA");
            consultarPorChoice.add("STATUS");
            consultarPorChoice.add("NUM_MESA");
            consultarPorChoice.add("CTPS_FUNC");
            consultarPorChoice.add("COD_COMANDA");
            consultarEmChoice.addItemListener(item);
            consultaFrame.pack();
            consultaFrame.setVisible(true);
            consultaFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }

    JPanel menuPanel = new JPanel(new GridLayout(3,1)); //panel pro menu com layout de 1 coluna e 2 linhas
    JButton insereButton = new JButton("Inserir");
    JButton consultaButton = new JButton("Consultar");
    JButton finalizaButton = new JButton("finalizar");

    public Restaurante(){ //janela 1
        super("Banco de dados restaurante");
        setPreferredSize(new Dimension(1000,600));
        setLayout(new GridLayout(3,3));
        insereButton.setBorder(BorderFactory.createLineBorder(Color.white, 15));
        insereButton.addActionListener(this);
        menuPanel.add(insereButton);
        consultaButton.setBorder(BorderFactory.createLineBorder(Color.white, 15));
        consultaButton.addActionListener(this);
        menuPanel.add(consultaButton);
        finalizaButton.setBorder(BorderFactory.createLineBorder(Color.white, 15));
        finalizaButton.addActionListener(this);
        menuPanel.add(finalizaButton);
        add(Box.createRigidArea(new Dimension(450, 200))); //1,1
        add(Box.createRigidArea(new Dimension(100, 200))); //1,2
        add(Box.createRigidArea(new Dimension(450, 200))); //1,3
        add(Box.createRigidArea(new Dimension(450, 200))); //2,1
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        add(menuPanel);
        add(Box.createRigidArea(new Dimension(450, 200))); //2,3
        add(Box.createRigidArea(new Dimension(450, 200))); //3,1
        add(Box.createRigidArea(new Dimension(100, 200))); //3,2
        add(Box.createRigidArea(new Dimension(450, 200))); //3,3
        iniciaBD();
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == insereButton) {
            insere();
        } else if (e.getSource() == consultaButton) {
            consulta();
        } else if (e.getSource() == finalizaButton) {
          System.exit(0);
        }
      }

    public static void main(String[] args ) {  
        new Restaurante();  
    }  

    void iniciaBD() {
        //inicia o BD
        try {
            Class.forName("org.hsql.jdbcDriver");
            con = DriverManager.getConnection("jdbc:HypersonicSQL:bd", "sa", "");
            stmt = con.createStatement();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "O driver do banco de dados não foi encontrado.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na iniciação do acesso ao banco de dados\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        //inicia a tabela
        //mesa (numero, cadeiras, localização, disponível)  4
        //Comanda (código, data, status, mesa_num, ctps_anfitriao, comand_prod) 6
        //pedido (código, quantidade, prod_cod) 3
        //produto (código, nome, preco, tipo) 4
        //9funcionário (ctps, nome, sexo, salario, turno, endereço, cargo, data_nascimento, data_admissao)
            try {
                stmt.executeUpdate("CREATE TABLE MESA (NUMERO INTEGER, NUMERO_DE_CADEIRAS INTEGER, LOCALIZACAO VARCHAR(30), DISPONIBILIDADE VARCHAR(30))");
                stmt.executeUpdate("CREATE TABLE COMANDA (CODIGO INTEGER, DATA VARCHAR(30), STATUS VARCHAR(30), NUM_MESA INTEGER, CTPS_FUNC INTEGER, COD_COMANDA INTEGER)");
                stmt.executeUpdate("CREATE TABLE FUNCIONARIO (CTPS INTEGER, NOME VARCHAR(30), SEXO VARCHAR(30), SALARIO INTEGER, TURNO VARCHAR(30), ENDERECO VARCHAR(30) , CARGO VARCHAR(30), DATA_NASC VARCHAR(30), DATA_ADM VARCHAR(30))");
                stmt.executeUpdate("CREATE TABLE PEDIDO (CODIGO INTEGER, QUANTIDADE INTEGER, COD_PRODUTO INTEGER, COD_COMANDA INTEGER)");
                stmt.executeUpdate("CREATE TABLE PRODUTO (CODIGO INTEGER, NOME VARCHAR(30), PRECO INTEGER, TIPO VARCHAR(30))");
                JOptionPane.showMessageDialog(null, "Tabela criada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, "Erro na criação da tabela.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                //JOptionPane.showMessageDialog(null, "Problema interno.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        //}
    }
    //inserir no BD
    ActionListener inserirListener = new ActionListener(){  //inserir no bd
        public void actionPerformed(ActionEvent evt){
            PreparedStatement pStmt;
            try {                   
                if(evt.getSource() == colocarMesa){  //colocar em mesa
                    pStmt = con.prepareStatement("SELECT * FROM "+ "MESA" + " WHERE "+ "NUMERO"+" LIKE \'"+ Integer.parseInt(numeroMesaField.getText()) +"\'");
                    ResultSet rs = pStmt.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Numero de Mesa já registrado.\nNão será salvo", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    pStmt = con.prepareStatement("INSERT INTO MESA VALUES (?, ?, ?, ?)");
                    pStmt.setInt(1, Integer.parseInt(numeroMesaField.getText()));
                    pStmt.setInt(2, Integer.parseInt(cadeirasText.getText()));
                    pStmt.setString(3, locChoice.getSelectedItem());
                    pStmt.setString(4, disponibilidadeChoice.getSelectedItem());
                    pStmt.executeUpdate();
                } else if(evt.getSource() == colocarFunc){ //funcionario
                    pStmt = con.prepareStatement("SELECT * FROM "+ "FUNCIONARIO" + " WHERE "+ "CTPS"+" LIKE \'"+ Integer.parseInt(ctpsField.getText()) +"\'");
                    ResultSet rs = pStmt.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "CTPS já registrado.\nNão será salvo", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    pStmt = con.prepareStatement("INSERT INTO FUNCIONARIO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    pStmt.setInt(1, Integer.parseInt(ctpsField.getText()));     //ctps
                    pStmt.setString(2, nomeFuncText.getText());                 //nome
                    pStmt.setString(3, sexoFuncChoice.getSelectedItem());       //sexo
                    pStmt.setInt(4, Integer.parseInt(salarioText.getText()));   //salario
                    pStmt.setString(5, turnoChoice.getSelectedItem());          //turno
                    pStmt.setString(6, enderecoFuncText.getText());             //endereço
                    pStmt.setString(7, cargoFuncChoice.getSelectedItem());      //cargo
                    pStmt.setString(8, dataNascFuncText.getText());                            //data de nascimento
                    pStmt.setString(9, dataAdmFuncText.getText());                            //data de admissão
                    pStmt.executeUpdate();
                } else if(evt.getSource() == colocarPedido){ //colocar em pedidos
                    ResultSet rs;
                    pStmt = con.prepareStatement("SELECT * FROM "+ "PRODUTO" + " WHERE "+ "CODIGO"+" LIKE \'"+ Integer.parseInt(pedidoCodProdField.getText()) +"\'");   //selecionando a tabela da mesa com num igual num indicado na comanda
                    rs=pStmt.executeQuery();
                    if(!rs.next()){
                        JOptionPane.showMessageDialog(null, "Codigo de Produto não cadastrado.\nNão será salvo", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    pStmt = con.prepareStatement("SELECT * FROM "+ "COMANDA" + " WHERE "+ "CODIGO"+" LIKE \'"+ Integer.parseInt(pedidoCodComandaField.getText()) +"\'");   //selecionando a tabela da mesa com num igual num indicado na comanda
                    rs=pStmt.executeQuery();
                    if(!rs.next()){
                        JOptionPane.showMessageDialog(null, "Codigo de Comanda não cadastrado.\nNão será salvo", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    pStmt = con.prepareStatement("SELECT * FROM "+ "PEDIDO" + " WHERE "+ "CODIGO"+" LIKE \'"+ Integer.parseInt(codPedidoField.getText()) +"\'");
                    rs = pStmt.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "codigo de pedido já registrado.\nNão será salvo", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    pStmt = con.prepareStatement("INSERT INTO PEDIDO VALUES (?, ?, ?, ?)");
                    pStmt.setInt(1, Integer.parseInt(codPedidoField.getText()));
                    pStmt.setInt(2, Integer.parseInt(quantidadePedidoText.getText()));
                    pStmt.setInt(3, Integer.parseInt(pedidoCodProdField.getText()));
                    pStmt.setInt(4, Integer.parseInt(pedidoCodComandaField.getText()));
                    pStmt.executeUpdate();
                } else if(evt.getSource() == colocarProd){ //colocar em produtos
                    pStmt = con.prepareStatement("SELECT * FROM "+ "PRODUTO" + " WHERE "+ "CODIGO"+" LIKE \'"+ Integer.parseInt(codProdField.getText()) +"\'");
                    ResultSet rs = pStmt.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "codigo de Produto já registrado.\nNão será salvo", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    pStmt = con.prepareStatement("INSERT INTO PRODUTO VALUES (?, ?, ?, ?)");
                    pStmt.setInt(1, Integer.parseInt(codProdField.getText()));
                    pStmt.setString(2, nomeProdText.getText());
                    pStmt.setInt(3, Integer.parseInt(precoProdText.getText()));
                    pStmt.setString(4, tipoProdChoice.getSelectedItem());
                    pStmt.executeUpdate();
                } else if(evt.getSource() == colocarComanda){ //colocar em comanda
                    pStmt = con.prepareStatement("SELECT * FROM "+ "COMANDA" + " WHERE "+ "CODIGO"+" LIKE \'"+ Integer.parseInt(codigoComandaText.getText()) +"\'");
                    ResultSet rs = pStmt.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Comanda já registrada.\nNão será salvo", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    pStmt = con.prepareStatement("SELECT * FROM "+ "MESA" + " WHERE "+ "NUMERO"+" LIKE \'"+ Integer.parseInt(mesaComandaText.getText()) +"\'");   //selecionando a tabela da mesa com num igual num indicado na comanda
                    rs = pStmt.executeQuery();
                    if(!rs.next()){
                        JOptionPane.showMessageDialog(null, "Mesa Inexistente.\nNão será salvo", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    pStmt = con.prepareStatement("SELECT * FROM "+ "FUNCIONARIO" + " WHERE "+ "CTPS"+" LIKE \'"+ Integer.parseInt(funcComandText.getText()) +"\'");   //selecionando a tabela da mesa com num igual num indicado na comanda
                    rs = pStmt.executeQuery();
                    if(!rs.next()){
                        JOptionPane.showMessageDialog(null, "Funcionario nao cadastrado.\nNão será salvo", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                                        
                    pStmt = con.prepareStatement("INSERT INTO COMANDA VALUES (?, ?, ?, ?, ?, ?)");
                    pStmt.setInt(1, Integer.parseInt(codigoComandaText.getText()));
                    pStmt.setString(2, dataComandaText.getText());
                    pStmt.setString(3, statusComandalChoice.getSelectedItem());
                    pStmt.setString(4, mesaComandaText.getText());
                    pStmt.setString(5, funcComandText.getText());
                    pStmt.setString(6, comandProdText.getText());
                    pStmt.executeUpdate();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Problema interno.2\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
              }
        }
    };
    ActionListener atualizarListener = new ActionListener(){  //EXCLUIR E CRIAR DE NOVO
        public void actionPerformed(ActionEvent evt){
            PreparedStatement pStmt;
            ResultSet rs;
            try{
                if(evt.getSource() == atualizarMesa){   
                    pStmt = con.prepareStatement("DELETE FROM MESA WHERE NUMERO LIKE \'"+ Integer.parseInt(numeroMesaField.getText())+"\'");
                    rs = pStmt.executeQuery();                    
                    pStmt = con.prepareStatement("INSERT INTO MESA VALUES (?, ?, ?, ?)");
                    pStmt.setInt(1, Integer.parseInt(numeroMesaField.getText()));
                    pStmt.setInt(2, Integer.parseInt(cadeirasText.getText()));
                    pStmt.setString(3, locChoice.getSelectedItem());
                    pStmt.setString(4, disponibilidadeChoice.getSelectedItem());
                    pStmt.executeUpdate();
                } else if(evt.getSource() == atualizarComanda){
                    pStmt = con.prepareStatement("DELETE FROM COMANDA WHERE CODIGO LIKE \'"+ Integer.parseInt(codigoComandaText.getText())+"\'");
                    rs = pStmt.executeQuery();    
                    pStmt = con.prepareStatement("INSERT INTO COMANDA VALUES (?, ?, ?, ?, ?, ?)");
                    pStmt.setInt(1, Integer.parseInt(codigoComandaText.getText()));
                    pStmt.setString(2, dataComandaText.getText());
                    pStmt.setString(3, statusComandalChoice.getSelectedItem());
                    pStmt.setString(4, mesaComandaText.getText());
                    pStmt.setString(5, funcComandText.getText());
                    pStmt.setString(6, comandProdText.getText());
                    pStmt.executeUpdate();
                } else if(evt.getSource() == atualizarFunc){
                    pStmt = con.prepareStatement("DELETE FROM FUNCIONARIO WHERE CTPS LIKE \'"+ Integer.parseInt(ctpsField.getText())+"\'");
                    rs = pStmt.executeQuery();    
                    pStmt = con.prepareStatement("INSERT INTO FUNCIONARIO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    pStmt.setInt(1, Integer.parseInt(ctpsField.getText()));     //ctps
                    pStmt.setString(2, nomeFuncText.getText());                 //nome
                    pStmt.setString(3, sexoFuncChoice.getSelectedItem());       //sexo
                    pStmt.setInt(4, Integer.parseInt(salarioText.getText()));   //salario
                    pStmt.setString(5, turnoChoice.getSelectedItem());          //turno
                    pStmt.setString(6, enderecoFuncText.getText());             //endereço
                    pStmt.setString(7, cargoFuncChoice.getSelectedItem());      //cargo
                    pStmt.setString(8, dataNascFuncText.getText());                            //data de nascimento
                    pStmt.setString(9, dataAdmFuncText.getText());                            //data de admissão
                    pStmt.executeUpdate();
                } else if(evt.getSource() == atualizarPedido){
                    pStmt = con.prepareStatement("DELETE FROM PEDIDO WHERE CODIGO LIKE \'"+ Integer.parseInt(codPedidoField.getText())+"\'");
                    rs = pStmt.executeQuery();    
                    pStmt = con.prepareStatement("INSERT INTO PEDIDO VALUES (?, ?, ?)");
                    pStmt.setInt(1, Integer.parseInt(codPedidoField.getText()));
                    pStmt.setInt(2, Integer.parseInt(quantidadePedidoText.getText()));
                    pStmt.setInt(3, Integer.parseInt(pedidoCodProdField.getText()));
                    pStmt.executeUpdate();
                } else if(evt.getSource() == atualizarProd){
                    pStmt = con.prepareStatement("DELETE FROM PRODUTO WHERE CODIGO LIKE \'"+ Integer.parseInt(codProdField.getText())+"\'");
                    rs = pStmt.executeQuery();    
                    pStmt.setInt(1, Integer.parseInt(codProdField.getText()));
                    pStmt.setString(2, nomeProdText.getText());
                    pStmt.setInt(3, Integer.parseInt(precoProdText.getText()));
                    pStmt.setString(4, tipoProdChoice.getSelectedItem());
                    pStmt.executeUpdate();
                }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema interno.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
          }
        }
    };
    //consultar no BD
    ActionListener consultarListener = new ActionListener(){  //inserir no bd
        public void actionPerformed(ActionEvent evt){
            PreparedStatement pStmt;
            String fonte = "";
            ResultSet rs;
            try {
                    resultadoConsulta.setText("");
                    fonte = campoConsultaText.getText();
                    pStmt = con.prepareStatement("SELECT * FROM "+ consultarEmChoice.getSelectedItem() + " WHERE "+ consultarPorChoice.getSelectedItem()+" LIKE \'"+ fonte+"\'");
                    rs = pStmt.executeQuery();                    
                    while (rs.next()) {              
                        consultarPorChoice.getItemCount();  //pega o numero de itens de consultaPorChoice
                        for(int i = 1;i<=consultarPorChoice.getItemCount();i++){
                            resultadoConsulta.append(consultarPorChoice.getItem(i-1) + " : " +rs.getString(i) + "\n");
                        }
                        resultadoConsulta.append("\n");
                    }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Problema interno.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    };
    //excluir no BD
    ActionListener excluirListener = new ActionListener(){  //inserir no bd
        public void actionPerformed(ActionEvent evt){
            PreparedStatement pStmt;
            String fonte = "";
            ResultSet rs;
            try {
                resultadoConsulta.setText("");
                fonte = campoConsultaText.getText();
                pStmt = con.prepareStatement("DELETE FROM "+ consultarEmChoice.getSelectedItem() + " WHERE "+ consultarPorChoice.getSelectedItem()+" LIKE \'"+ fonte+"\'");
                rs = pStmt.executeQuery();                    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema interno.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        }
    };

    ItemListener item = new ItemListener(){  //inserir no bd
        public void itemStateChanged(ItemEvent evt){
            if(consultarEmChoice.getSelectedItem() == "MESA"){
                consultarPorChoice.removeAll();
                consultarPorChoice.add("NUMERO");
                consultarPorChoice.add("NUMERO_DE_CADEIRAS");
                consultarPorChoice.add("LOCALIZACAO");
                consultarPorChoice.add("DISPONIBILIDADE");
            } else if(consultarEmChoice.getSelectedItem() == "COMANDA"){
                consultarPorChoice.removeAll();
                consultarPorChoice.add("CODIGO");
                consultarPorChoice.add("DATA");
                consultarPorChoice.add("STATUS");
                consultarPorChoice.add("NUM_MESA");
                consultarPorChoice.add("CTPS_FUNC");
                consultarPorChoice.add("COD_COMANDA");
            } else if(consultarEmChoice.getSelectedItem() == "FUNCIONARIO"){
                consultarPorChoice.removeAll();
                consultarPorChoice.add("CTPS");
                consultarPorChoice.add("NOME");
                consultarPorChoice.add("SEXO");
                consultarPorChoice.add("SALARIO");
                consultarPorChoice.add("TURNO");
                consultarPorChoice.add("ENDERECO");
                consultarPorChoice.add("CARGO");
                consultarPorChoice.add("DATA_NASC");
                consultarPorChoice.add("DATA_ADM");
            } else if(consultarEmChoice.getSelectedItem() == "PEDIDO"){
                consultarPorChoice.removeAll();
                consultarPorChoice.add("CODIGO");
                consultarPorChoice.add("QUANTIDADE");
                consultarPorChoice.add("COD_PRODUTO");
            } else if(consultarEmChoice.getSelectedItem() == "PRODUTO"){
                consultarPorChoice.removeAll();
                consultarPorChoice.add("CODIGO");
                consultarPorChoice.add("NOME");
                consultarPorChoice.add("PRECO");
                consultarPorChoice.add("TIPO");
            }
        }
    };
}
