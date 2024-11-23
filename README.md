# Capstone Project
<h2>BugWiki: A Home Pest Identification and Management App</h2>

## Overview
BugWiki is an innovative app designed to identify pests and provide tailored solutions for pest control in households. By leveraging deep learning for image analysis, the app aims to help users recognize pests, learn about their characteristics, and discover effective prevention and extermination methods. BugWiki ensures a comprehensive pest management experience by combining multiple features, from pest identification to locating nearby pest control services.

## Motivation
- The Growing Pest Problem: Urban areas, including Seoul, have experienced increasing infestations of pests like "love bugs," causing significant inconvenience to residents.
- Challenges in Pest Control: Limited access to centralized information about pest species and effective extermination methods often delays timely responses.
- Objective: Minimize pest-related damage and enhance the convenience of pest prevention and control through a user-friendly app.

## Features
1. Pest Identification via Image Analysis
- Identify pests using photos captured via the app or selected from the gallery.
- Built using a MobileNet model with:
  - Accuracy: 80% (trained on 41 pest categories with image augmentation).
  - Training-Test Split: 85% : 15%.
- Interactive Features:
  - Zoom functionality for detailed image capture.
  - Detailed pest information, including harm level and habits.

2. Pest Control Recommendations
- Provide extermination methods and prevention tips tailored to each pest species.
- Recommend popular pest control products with links to purchase them.

3. Q&A for Pest Identification
- Predict pest species through a question-and-answer feature based on observed characteristics.
- Show blurred images to maintain user comfort.

4. Nearby Pest Control Services
- Locate pest control services around the user’s current location using the Seoul Open Data Platform and Google Maps API.
- Display detailed information, including business status, addresses, and contact numbers.

## Development
- Android Studio: App development.
- Firebase: Database management and authentication.
- Google Teachable Machine: Model training and deployment.
- Google Maps API: Location-based services.

## System Architecture
- Integrated database for pest information, location data, and user inputs.
- Key modules:
  - Pest search page for image-based identification.
  - Information page for extermination methods.
  - Location services for nearby pest control options.

## Screenshots
1. Pest Identification Page:
  - Identify pests via captured or gallery images.
  - Provide detailed information and extermination methods.
2. Q&A Page:
  - Assist users in identifying pests based on characteristics.
3. Nearby Pest Control Services:
  - Display pest control service providers with addresses and contact details.

## References: Additional File Descriptions
▶ MainActivity : Camera/Gallery + Image Classification Results<br>
▶ HomeActivity : main page<br>
▶ MapsActivity : Pest Control Company Page (All uploaded Kotlin files are related to the pest control company)<br>

▶ activity_main.xml : Camera/Gallery + Image Classification Results layout <br>
▶ activity_home.xml : main page layout<br>
▶ activity_maps.xml : Pest Control Company layout<br>

<br>
▶ Bug.java, CustomAdapter.java, InfoActivity.java, activity_info.xml, list_item.xml: Information <br>

<h6>Model Description</h6>
▶ model_대분류_tflite: Main Category google teachable machine model tflite file <br>
▶ model_소분류_tflite: SubCategory google teachable machine model tflite file <br>
