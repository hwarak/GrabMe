//
//  SelectCategoryViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/03/28.
//

import UIKit
import Lottie

class SelectCategoryViewController: UIViewController {

    //MARK: - Properties
    let animationView = AnimationView()
    
    //MARK: - LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        //setupAnimation()
    }
    
    //MARK: - Actions
    func setupAnimation() {
        animationView.animation = Animation.named("circleRedButton")
        animationView.frame = view.bounds
        animationView.backgroundColor = .clear
        animationView.contentMode = .scaleAspectFill
        animationView.loopMode = .loop
        animationView.isUserInteractionEnabled = false
        animationView.frame = CGRect(x: 20, y: 80, width: 300, height: 300)
        view.addSubview(animationView)
        animationView.play()
        
    }

}
