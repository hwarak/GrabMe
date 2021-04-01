//
//  AuthenticationViewModel.swift
//  GrabMe
//
//  Created by Tony Jung on 2021/03/14.
//

import UIKit

protocol FormViewModel {
    func updateForm()
}

protocol AuthenticationViewModel {
    var FormIsValid: Bool { get }
    var buttonBackgroundColor: UIColor { get }
    var buttonTitleColor: UIColor { get }
}

class RegistrationViewModel: AuthenticationViewModel {
    var phoneNumber: String?
    var userStatus: Bool?
    var countryCode: String?

    
    private let manager = AuthenticationManager.shared
    
    var FormIsValid: Bool {
        return phoneNumber?.isEmpty == false && countryCode?.isEmpty == false && userStatus == true
    }
    
    var buttonBackgroundColor: UIColor {
        return FormIsValid ? #colorLiteral(red: 0.3001908362, green: 0.411488235, blue: 0.5798021555, alpha: 1) : #colorLiteral(red: 0.3722569346, green: 0.5067420006, blue: 0.7058593631, alpha: 1).withAlphaComponent(0.5)
    }
    
    var buttonTitleColor: UIColor {
        return FormIsValid ? .white : UIColor(white: 1, alpha: 0.67)
    }
    
    // userAuth
    func checkExistingUser(status: Int, phone: String){
        manager.checkExistingUser(status: status, phone: phone)
    }
    
    func checkVerification(code: String) -> Bool {
        return code == manager.userAuth?.data.code ? true : false
    }
    
    func signUpUser(name: String){
        manager.signUpUser(name: name)
    }

    
}
