//
//  CategoryListDatailViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/11.
//

import UIKit
import Kingfisher

class CategoryListDetailViewController: UIViewController {
    //MARK: - Properties
    private var list = ["hello", "two", "three", "four"]
    private var time = ["9:00am","10:00am","11:00am","12:00pm","13:00pm","14:00pm","15:00pm","16:00pm","17:00pm","18:00pm"]
    var selectedIndexPath = IndexPath()
    @IBOutlet weak var showDate: UICollectionView!
    private var dateIdentifier = "date"
    
    @IBOutlet weak var showTime: UICollectionView!
    private var timeIdentifier = "time"
    
    private var viewModel = CategoryListViewModel()
    @IBOutlet weak var placeImage: UIImageView!
    @IBOutlet weak var placeTitle: UILabel!
    @IBOutlet weak var placeLocation: UILabel!
    @IBOutlet weak var placePhone: UILabel!
    @IBOutlet weak var placeDescription: UILabel!
    
    //MARK: - LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel.sevendaysAfterToday()
        configureUI()
        showTime.isHidden = true
        
        
    }
    
    //MARK: - Helpers
    private func configureUI(){
        placeTitle.text = viewModel.categoryDetail.title
        placeLocation.text = viewModel.categoryDetail.address
        placePhone.text = viewModel.categoryDetail.phone
        placeDescription.text = viewModel.categoryDetail.description
        let url = URL(string: viewModel.categoryDetail.thumbnail)
        placeImage.kf.setImage(with: url)
        
        
        
    }
    
    //MARK: - Actions
    @IBAction func backButtonTapped(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    
}

extension CategoryListDetailViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        
        if collectionView == self.showDate {
            return viewModel.days.count
        }
        else {
            return time.count
            
        }
       
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        if collectionView == self.showDate {
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: dateIdentifier, for: indexPath) as? CategoryDetailDateCollectionViewCell else { return UICollectionViewCell() }
            cell.date.text = "\(viewModel.days[indexPath.row])"
            cell.day.text = "\(viewModel.daysInText[indexPath.row])"

            
            return cell
        }
        else {
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: timeIdentifier, for: indexPath) as? CategoryDetailTimeCollectionViewCell else {return UICollectionViewCell()}
            cell.categoryDetailTime.text = time[indexPath.row]
                return cell
                
            
        }
    
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if collectionView == showDate {
            showTime.isHidden = false
            let cell = collectionView.cellForItem(at: indexPath) as? CategoryDetailDateCollectionViewCell
            cell?.view.backgroundColor = .lightGray
            selectedIndexPath = indexPath//cell?.backgroundColor = UIColor.blue
            collectionView.allowsMultipleSelection = false
        }
        
        else {
            let vc = self.storyboard?.instantiateViewController(identifier: "modal") as! ModalViewController
            present(vc, animated: true, completion: nil)
        }
    }
    
    
}

extension CategoryListDetailViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        if collectionView == showDate {
            return CGSize(width: 80, height: 100)
        }
        else {
            return CGSize(width: 120, height: 60)
        }
       

    }
}
