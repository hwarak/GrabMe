//
//  ViewController.swift
//  GrabMe
//
//  Created by Tony Jung on 2021/02/23.
//

import UIKit

class RegistrationController: UIViewController {

    // MARK: - outlets
    @IBOutlet weak var countryTextField: UITextField!
    @IBOutlet weak var phoneTextField: UITextField!
    @IBOutlet weak var registraionButton: UIButton!
    @IBOutlet var userStatus: [UIButton]!
    
    // MARK: - properties
    var selectedCountry: String?
    var listOfCountry = ["🇰🇷Korea","🇯🇵Japan","🇺🇸USA"]
    
    // MARK: - Lifecycles
    override func viewDidLoad() {
        super.viewDidLoad()
        self.createAndSetupPickerView()
        self.dismissAndClosePickerView()
    }
   
}

// MARK: - Actitons
extension RegistrationController {
    
    @IBAction func isButtonClicked(_ sender: UIButton) {
        if sender.tag == 0 {
            userStatus[0].isSelected = true
            userStatus[1].isSelected = false
        } else {
            userStatus[1].isSelected = true
            userStatus[0].isSelected = false
        }
    }
    
    func configureUI(){
        
    }
    func createAndSetupPickerView(){
        let pickerView = UIPickerView()
        pickerView.delegate = self
        pickerView.dataSource = self
        self.countryTextField.inputView = pickerView
    }
    
    @objc func dismissAction() {
        self.view.endEditing(true)
    }
    
    func dismissAndClosePickerView(){
        let toolBar = UIToolbar()
        toolBar.sizeToFit()
        
        let button = UIBarButtonItem(title: "Done", style: .plain, target: self, action: #selector(self.dismissAction))
        toolBar.setItems([button], animated: true)
        toolBar.isUserInteractionEnabled = true
        self.countryTextField.inputAccessoryView = toolBar
    }
    
    func getCountryCode(country: String) -> String{
        switch country {
        case "🇰🇷Korea": return "🇰🇷+82"
        case "🇯🇵Japan": return "🇯🇵+81"
        case "🇺🇸USA": return "🇺🇸+1"
        default: return "🇺🇸+1"
        }
    }
}

// MARK: - UIPicker for selecting Country code
extension RegistrationController: UIPickerViewDelegate, UIPickerViewDataSource, UITextFieldDelegate {
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return self.listOfCountry.count
    }
 
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return self.listOfCountry[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        self.selectedCountry = self.listOfCountry[row]
        self.countryTextField.text = getCountryCode(country: selectedCountry ?? " ")
        
    }
}

