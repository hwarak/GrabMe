//
//  ViewController.swift
//  GrabMe
//
//  Created by Tony Jung on 2021/02/23.
//Doyeon

import UIKit

class RegistrationController: UIViewController {

    // MARK: - outlets
    @IBOutlet weak var countryTextField: UITextField!
    @IBOutlet weak var phoneTextField: UITextField!
    @IBOutlet weak var registraionButton: UIButton!
    @IBOutlet var userStatus: [UIButton]!
    
 
    // MARK: - properties
    private var viewModel = RegistrationViewModel()
    private var selectedCountry: String?
    private var listOfCountry = ["🇰🇷Korea","🇯🇵Japan","🇺🇸USA"]
    private var status: Int?
    


    
    
    // MARK: - Lifecycles
    override func viewDidLoad() {
        super.viewDidLoad()
        self.createAndSetupPickerView()
        self.dismissAndClosePickerView()
        self.configurationNotificationObservers()
        self.configureUI()
    }
}

extension RegistrationController {
    // MARK: - Helpers
    func configureUI(){
        updateForm()
    }
    
    func configurationNotificationObservers(){
        phoneTextField.addTarget(self, action: #selector(textDidChange), for: .editingChanged)
        countryTextField.addTarget(self, action: #selector(textDidChange), for: .editingChanged)
    }
    
    // MARK: - Actitons
    @IBAction func submitButtonTapped(_ sender: UIButton) {

        guard let phone = phoneTextField.text else { return }
        guard let stat = status else { return }

            self.viewModel.checkExistingUser(status: stat, phone: phone)
       
                let vc = self.storyboard?.instantiateViewController(identifier: "verifySNS") as! VerifySNSViewController
                vc.modalPresentationStyle = .fullScreen
             //   self.present(vc, animated: true, completion: nil)

        self.navigationController?.pushViewController(vc, animated: true)
        print("sent")

    }
    
    @IBAction func isButtonClicked(_ sender: UIButton) {
        viewModel.userStatus = true
        userStatus[0].isSelected = sender.tag == 0
        userStatus[1].isSelected = sender.tag == 1
        status = sender.tag
        updateForm()
    }
    
    func createAndSetupPickerView(){
        let pickerView = UIPickerView()
        pickerView.delegate = self
        pickerView.dataSource = self
        
        countryTextField.inputView = pickerView
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
    
    @objc func dismissAction() {
        self.view.endEditing(true)
    }
    
    @objc func textDidChange(sender: UITextField){
        if sender == phoneTextField {
            viewModel.phoneNumber = sender.text
        }
        else if sender == countryTextField {
            viewModel.countryCode = sender.text
        }
        
        updateForm()
    }
}
// MARK: - FormViewModel
extension RegistrationController: FormViewModel {
    
    func updateForm() {
        registraionButton.backgroundColor = viewModel.buttonBackgroundColor
        registraionButton.setTitleColor(viewModel.buttonTitleColor, for: .normal)
        registraionButton.isEnabled = viewModel.FormIsValid
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
        guard let country = selectedCountry else { return }
        self.countryTextField.text = getCountryCode(country: country)
        print(country)
        
        viewModel.countryCode = countryTextField.text
        updateForm()
    }
}

