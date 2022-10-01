module com.udacity.hotel {
	// Add your code here
    exports com.udacity.hotel.search;
    exports com.udacity.hotel.model;
    opens com.udacity.hotel.model to com.udacity.packagesearch;
}