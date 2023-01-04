package GUI

import fileReader.model.{Airport, Country, Runway}
import fileReader.service.{Quering, Report}
import storing.{Storage , Database}

import java.awt.{Color, BorderLayout, Dimension, Font}
import javax.swing.{Box, BoxLayout, JButton, JFrame, JLabel, JPanel, JScrollPane, JTextArea, JTextField}

object GUI {

  def mainFrame(stoAir : Storage[Airport], stoCountries : Storage[Country], stoRun : Storage[Runway]): Unit ={
    val frame = new JFrame("Airport Project")

    val introductionPanel = panelStyle(frame, BorderLayout.NORTH)

    val textArea = new JLabel("Choose an option: ")
    textArea.setForeground(Color.WHITE)
    introductionPanel.add(textArea, BorderLayout.NORTH)

    val buttonsPanel = new JPanel()

    val queryButton = buttonStyle("(1) Query")
    buttonsPanel.add(queryButton)
    val reportButton = buttonStyle("(2) Reports")
    buttonsPanel.add(reportButton)
    val query2Button = buttonStyle("(3) Query2")
    buttonsPanel.add(query2Button)

    introductionPanel.add(buttonsPanel, BorderLayout.CENTER)

    val interactionPanel = panelStyle(frame, BorderLayout.CENTER)

    val selection = new JTextArea("")
    selection.setForeground(Color.WHITE)
    selection.setBackground(new Color(240, 128, 128))
    interactionPanel.add(selection, BorderLayout.NORTH)

    def queryGUI(): Unit ={
      queryFrame(stoAir, stoCountries, stoRun)
    }

    queryButton.addActionListener(e => queryGUI())

    def reportGUI(): Unit ={
      reportFrame(stoAir, stoCountries, stoRun)
    }

    reportButton.addActionListener(e => reportGUI())

    def query2GUI(): Unit = {
      query2Frame(stoAir, stoCountries, stoRun)
    }

    query2Button.addActionListener(e => query2GUI())

    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
  }

  def newFrame(results: String) = {
    val frame = new JFrame("Result of your research")

    val textArea = new JTextArea("\n")
    val purple = new Color(240, 128, 128)
    textArea.setForeground(Color.WHITE)
    textArea.setBackground(purple)

    val scrollPane = new JScrollPane(textArea)
    scrollPane.setForeground(Color.WHITE)
    scrollPane.setBackground(purple)
    textArea.append(results)

    frame.add(scrollPane)

    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
  }



  def queryFrame(stoAir : Storage[Airport], stoCountries : Storage[Country], stoRun : Storage[Runway])= {
    val frame = new JFrame("Result of your research")
    frame.setSize(300, 200)

    val mainPanel = panelStyle(frame, BorderLayout.CENTER)

    val selection = new JTextArea("")
    selection.setForeground(Color.WHITE)
    selection.setBackground(new Color(240, 128, 128))
    mainPanel.add(selection, BorderLayout.NORTH)


    val textField = new JTextField(10)

    val queryText = "\n ************* Query ************* \n"
    selection.setText(queryText)

    val countryText = "Enter a Country Name or Country Code: "
    selection.append(countryText)

    mainPanel.add(textField, BorderLayout.CENTER)

    def queryResult(): Unit = {
      val research = textField.getText
      val airports = Quering.readCountry(stoAir, stoCountries, stoRun, research)
      newFrame(resultToString(airports))
    }

    textField.addActionListener(e => queryResult())

    frame.add(mainPanel)
    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
  }

  def reportFrame(stoAir : Storage[Airport], stoCountries : Storage[Country], stoRun : Storage[Runway])= {
    val frame = new JFrame("Result of your research")
    frame.setSize(300, 200)

    val mainPanel = panelStyle(frame, BorderLayout.CENTER)

    val selection = new JTextArea("")
    selection.setForeground(Color.WHITE)
    selection.setBackground(new Color(240, 128, 128))
    mainPanel.add(selection, BorderLayout.NORTH)

    val reportText = "\n ************* Report ************* \n"
    selection.setText(reportText)

    val reportSelection = "Choose an option: "
    selection.append(reportSelection)

    val reportPanel = panelStyle(frame, BorderLayout.SOUTH)

    val reportFirstButton = buttonStyle("(1) 10 countries with highest/lowest number of airports ")
    val reportSecondButton = buttonStyle("                    (2) Type of runways per country                    ")
    val reportThirdButton = buttonStyle("             (3) Top 10 most common runway latitude            ")

    val reportButtonsPanel = new JPanel()
    reportButtonsPanel.setBackground(new Color(240, 128, 128))
    reportButtonsPanel.setLayout(new BoxLayout(reportButtonsPanel, BoxLayout.Y_AXIS))
    reportButtonsPanel.add(Box.createVerticalStrut(10))

    reportButtonsPanel.add(reportFirstButton)
    reportButtonsPanel.add(Box.createVerticalStrut(10))
    reportButtonsPanel.add(reportSecondButton)
    reportButtonsPanel.add(Box.createVerticalStrut(10))
    reportButtonsPanel.add(reportThirdButton)

    reportPanel.add(reportButtonsPanel, BorderLayout.CENTER)

    def reportResult(buttonSelection : Int): Unit={
      val results = Report.readOption(stoAir, stoCountries, stoRun, buttonSelection)
      newFrame(resultToString(results))
    }

    reportFirstButton.addActionListener(e => reportResult(1))
    reportSecondButton.addActionListener(e => reportResult(2))
    reportThirdButton.addActionListener(e => reportResult(3))

    frame.add(mainPanel)
    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
  }

  def query2Frame(value: Storage[Airport], value1: Storage[Country], value2: Storage[Runway]): Unit ={
    val frame = new JFrame("Result of your research")
    frame.setSize(300, 200)

    val mainPanel = panelStyle(frame, BorderLayout.CENTER)

    val selection = new JTextArea("")
    selection.setForeground(Color.WHITE)
    selection.setBackground(new Color(240, 128, 128))
    mainPanel.add(selection, BorderLayout.NORTH)

    val query2Text = "\n ************* Query2 ************* \n"
    selection.setText(query2Text)

    val query2Selection = "Choose an option: "
    selection.append(query2Selection)

    val reportPanel = panelStyle(frame, BorderLayout.SOUTH)

    val query2FirstButton = buttonStyle("(1) Tables Description")
    val query2SecondButton = buttonStyle("   (2) Query Example   ")
    val query2ThirdButton = buttonStyle("      (3) Free Query       ")

    val reportButtonsPanel = new JPanel()
    reportButtonsPanel.setBackground(new Color(240, 128, 128))
    reportButtonsPanel.setLayout(new BoxLayout(reportButtonsPanel, BoxLayout.Y_AXIS))
    reportButtonsPanel.add(Box.createVerticalStrut(10))

    reportButtonsPanel.add(query2FirstButton)
    reportButtonsPanel.add(Box.createVerticalStrut(10))
    reportButtonsPanel.add(query2SecondButton)
    reportButtonsPanel.add(Box.createVerticalStrut(10))
    reportButtonsPanel.add(query2ThirdButton)

    reportPanel.add(reportButtonsPanel, BorderLayout.CENTER)

    def query2Result(buttonSelection: Int): Unit = {
      val results = Database.readOptionDataBase(buttonSelection)
      newFrame(resultToString(results))

    }

    query2FirstButton.addActionListener(e => query2Result(1))
    query2SecondButton.addActionListener(e => query2Result(2))
    query2ThirdButton.addActionListener(e => query2Submit())

    frame.add(mainPanel)
    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
  }

  def query2Submit() = {
    val frame = new JFrame("Result of your research")
    frame.setSize(300, 200)

    val mainPanel = panelStyle(frame, BorderLayout.CENTER)

    val selection = new JTextArea("")
    selection.setForeground(Color.WHITE)
    selection.setBackground(new Color(240, 128, 128))
    mainPanel.add(selection, BorderLayout.NORTH)


    val textField = new JTextField(10)

    val queryText = "\n ************* Query2 ************* \n"
    selection.setText(queryText)

    val queryField = "Enter a query: "
    selection.append(queryField)

    mainPanel.add(textField, BorderLayout.CENTER)

    def queryExecFrame(): Unit = {
      val research = textField.getText
      val result = Database.execQuery(research)
      newFrame(resultToString(result))
    }

    textField.addActionListener(e => queryExecFrame())

    frame.add(mainPanel)
    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)

  }

  def buttonStyle(description: String) = {
    val indigo = new Color(205, 92, 92)
    val BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 20)
    val button = new JButton(description)
    button.setFont(BUTTON_FONT)
    button.setForeground(Color.WHITE)
    button.setBackground(indigo)
    button
  }

  def panelStyle(frame: JFrame, orientation: String) = {
    val purple = new Color(240, 128, 128)
    val panel = new JPanel()
    panel.setForeground(Color.WHITE)
    panel.setBackground(purple)
    frame.add(panel, orientation)
    panel
  }

  def resultToString(result: List[String]): String = result match {
    case Nil => ""
    case t :: q => t + "\n" + resultToString(q)
  }
}