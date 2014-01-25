var reducePoints = function(points, doc) {
	var total = 0;
	Object.keys(points).forEach(function(key) {
		total += key * points[key];
	});
	db.penaltyPostcode.update(
		{ "_id" : doc._id },
		{$set: { "Total" : total}}
	);
};

db.penaltyPostcode.find().forEach(function (penaltyPostcodeDoc) {
	reducePoints(penaltyPostcodeDoc.PointsFrequency, penaltyPostcodeDoc);
});
