//
//  CategoryViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/01.
//

import UIKit
import CoreLocation

class CategoryViewController: UIViewController {

    //MARK: - Properties
    let locationManager = CLLocationManager()
    let viewModel = CategoryListViewModel()
    @IBOutlet weak var profileImage: UIButton!
    
    //MARK: - LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        checkLocationServices()
        // Do any additional setup after loading the view.
        profileImage.layer.borderColor = UIColor.gray.cgColor
        profileImage.layer.borderWidth = 2
    }
    


    
    //MARK: - Helpers
    func setupLocationManager() {
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
    
        locationManager.startUpdatingLocation()
    }
    
    func checkLocationServices() {
        if CLLocationManager.locationServicesEnabled() {
            setupLocationManager()
            checkLocationAuthrization()
        } else {
            //show alert letting the user know they have to turn this on
        }
    }
    
    func checkLocationAuthrization(){
        switch locationManager.authorizationStatus {
        case .authorizedWhenInUse: break
        case .denied:
            // Show alert instructing them how to turn on permission
            break
        case .notDetermined:
            locationManager.requestWhenInUseAuthorization()
            break
        case .restricted:
            // show an alert
            break
        case .authorizedAlways:
            break
        @unknown default:
            break
        }
    }
    
    
    //MARK: - Actions
    
    @IBAction func categoryButtonTapped(_ sender: UIButton) {

        let vc = self.storyboard?.instantiateViewController(identifier: "CategoryList") as! CategoryListViewController
        vc.modalPresentationStyle = .fullScreen
        
        let categoryName = findCategory(category: sender.tag)
        vc.category = categoryName

        
        let currentLocation = self.locationManager.location!.coordinate
        
        viewModel.requestList(category: sender.tag, lat: currentLocation.latitude, long: currentLocation.longitude,completion: {
            DispatchQueue.main.async {
            self.navigationController?.pushViewController(vc, animated: true)
             //   self.present(vc, animated: true, completion: nil)
            }
        })
        
        print("🔴 current location lat: \(currentLocation.latitude), long: \(currentLocation.longitude)")
    }
    
    //MARK: - Helpers
    func findCategory(category: Int) -> String{
        let category = Categorysss(rawValue: category)
        guard let categoryName = category?.getCategory() else { return "" }
        return categoryName
    }
}

extension CategoryViewController: CLLocationManagerDelegate {
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        //guard let locValue: CLLocationCoordinate2D = manager.location?.coordinate else { return }
        //   print("🟣 lat: \(locValue.latitude), long: \(locValue.longitude)")
        
        
    }
    
    func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
        checkLocationAuthrization()
    }
}
