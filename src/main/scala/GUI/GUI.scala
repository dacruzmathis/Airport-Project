package GUI

import fileReader.model.{Airport, Country, Runway}
import fileReader.service.{Quering, Report}
import storing.Storage

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

    introductionPanel.add(buttonsPanel, BorderLayout.CENTER)

    val interactionPanel = panelStyle(frame, BorderLayout.CENTER)

    val selection = new JTextArea("")
    selection.setForeground(Color.WHITE)
    selection.setBackground(new Color(240, 128, 128))
    interactionPanel.add(selection, BorderLayout.NORTH)

    //Query Tools
    val textField = new JTextField(10)

    //Report Tools
    val reportFirstButton = buttonStyle("(1) 10 countries with highest/lowest number of airports")
    val reportSecondButton = buttonStyle("                    (2) Type of runways per country                    ")
    val reportThirdButton = buttonStyle("             (3) Top 10 most common runway latitude            ")

    def stateButtons(state:Boolean)={
      queryButton.setEnabled(!state)
      reportButton.setEnabled(state)
      reportFirstButton.setVisible(!state)
      reportSecondButton.setVisible(!state)
      reportThirdButton.setVisible(!state)
      textField.setVisible(state)
    }

    def queryGUI(): Unit ={
      stateButtons(true)

      val queryText = "\n ************* Query ************* \n"
      selection.setText(queryText)

      val countryText = "Enter a Country Name or Country Code: "
      selection.append(countryText)

      interactionPanel.add(textField, BorderLayout.CENTER)

      def queryResult(): Unit={
        val research = textField.getText
        val airports = Quering.readCountry(stoAir, stoCountries, stoRun, research).toString()
        newFrame(airports)
      }

      textField.addActionListener(e => queryResult())
    }

    queryButton.addActionListener(e => queryGUI())

    def reportGUI(): Unit ={
      stateButtons(false)

      val reportText = "\n ************* Report ************* \n"
      selection.setText(reportText)

      val reportSelection = "Choose an option: "
      selection.append(reportSelection)

      val reportPanel = panelStyle(frame, BorderLayout.SOUTH)

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
        newFrame(results)
      }

      reportFirstButton.addActionListener(e => reportResult(1))
      reportSecondButton.addActionListener(e => reportResult(2))
      reportThirdButton.addActionListener(e => reportResult(3))
    }

    reportButton.addActionListener(e => reportGUI())

    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
  }

  def newFrame(results : String) = {
    val frame = new JFrame("Result of your research")
    frame.setSize(300, 200)

    val textArea = new JTextArea("\n")
    val purple = new Color(240, 128, 128)
    textArea.setForeground(Color.WHITE)
    textArea.setBackground(purple)

    val scrollPane = new JScrollPane(textArea)
    scrollPane.setForeground(Color.WHITE)
    scrollPane.setBackground(purple)
    textArea.append(results)

    frame.add(scrollPane)
    frame.setVisible(true)
  }

  def buttonStyle(description : String)={
    val indigo = new Color(205, 92, 92)
    val BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 20)
    val button = new JButton(description)
    button.setFont(BUTTON_FONT)
    button.setForeground(Color.WHITE)
    button.setBackground(indigo)
    button
  }

  def panelStyle(frame : JFrame, orientation : String)={
    val purple = new Color(240, 128, 128)
    val panel = new JPanel()
    panel.setForeground(Color.WHITE)
    panel.setBackground(purple)
    frame.add(panel, orientation)
    panel
  }
}
