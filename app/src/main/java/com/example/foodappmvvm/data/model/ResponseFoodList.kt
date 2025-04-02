package com.example.foodappmvvm.data.model


import com.google.gson.annotations.SerializedName

data class ResponseFoodList(
    @SerializedName("meals")
    val meals: List<Meal>
) {
    data class Meal(
        @SerializedName("dateModified")
        val dateModified: Any?, // null
        @SerializedName("idMeal")
        val idMeal: String?, // 52964
        @SerializedName("strArea")
        val strArea: String?, // Indian
        @SerializedName("strCategory")
        val strCategory: String?, // Breakfast
        @SerializedName("strCreativeCommonsConfirmed")
        val strCreativeCommonsConfirmed: Any?, // null
        @SerializedName("strImageSource")
        val strImageSource: Any?, // null
        @SerializedName("strIngredient1")
        val strIngredient1: String?, // Butter
        @SerializedName("strIngredient10")
        val strIngredient10: String?, // Eggs
        @SerializedName("strIngredient11")
        val strIngredient11: String?, // Parsley
        @SerializedName("strIngredient12")
        val strIngredient12: String?, // Lemon
        @SerializedName("strIngredient13")
        val strIngredient13: String?,
        @SerializedName("strIngredient14")
        val strIngredient14: String?,
        @SerializedName("strIngredient15")
        val strIngredient15: String?,
        @SerializedName("strIngredient16")
        val strIngredient16: String?,
        @SerializedName("strIngredient17")
        val strIngredient17: String?,
        @SerializedName("strIngredient18")
        val strIngredient18: String?,
        @SerializedName("strIngredient19")
        val strIngredient19: String?,
        @SerializedName("strIngredient2")
        val strIngredient2: String?, // Onion
        @SerializedName("strIngredient20")
        val strIngredient20: String?,
        @SerializedName("strIngredient3")
        val strIngredient3: String?, // Cardamom
        @SerializedName("strIngredient4")
        val strIngredient4: String?, // Turmeric
        @SerializedName("strIngredient5")
        val strIngredient5: String?, // Cinnamon Stick
        @SerializedName("strIngredient6")
        val strIngredient6: String?, // Bay Leaf
        @SerializedName("strIngredient7")
        val strIngredient7: String?, // Basmati Rice
        @SerializedName("strIngredient8")
        val strIngredient8: String?, // Chicken Stock
        @SerializedName("strIngredient9")
        val strIngredient9: String?, // Smoked Haddock
        @SerializedName("strInstructions")
        val strInstructions: String?, // Melt 50g butter in a large saucepan (about 20cm across), add 1 finely chopped medium onion and cook gently over a medium heat for 5 minutes, until softened but not browned.Stir in 3 split cardamom pods, ¼ tsp turmeric, 1 small cinnamon stick and 2 bay leaves, then cook for 1 minute.Tip in 450g basmati rice and stir until it is all well coated in the spicy butter.Pour in 1 litre chicken or fish stock, add ½ teaspoon salt and bring to the boil, stir once to release any rice from the bottom of the pan. Cover with a close-fitting lid, reduce the heat to low and leave to cook very gently for 12 minutes.Meanwhile, bring some water to the boil in a large shallow pan. Add 750g un-dyed smoked haddock fillet and simmer for 4 minutes, until the fish is just cooked. Lift it out onto a plate and leave until cool enough to handle.Hard-boil 3 eggs for 8 minutes.Flake the fish, discarding any skin and bones. Drain the eggs, cool slightly, then peel and chop. Uncover the rice and remove the bay leaves, cinnamon stick and cardamom pods if you wish to. Gently fork in the fish and the chopped eggs, cover again and return to the heat for 2-3 minutes, or until the fish has heated through.Gently stir in almost all the 3 tbsp chopped fresh parsley, and season with a little salt and black pepper to taste. Serve scattered with the remaining parsley and garnished with 1 lemon, cut into wedges.
        @SerializedName("strMeal")
        val strMeal: String?, // Smoked Haddock Kedgeree
        @SerializedName("strMealAlternate")
        val strMealAlternate: Any?, // null
        @SerializedName("strMealThumb")
        val strMealThumb: String?, // https://www.themealdb.com/images/media/meals/1550441275.jpg
        @SerializedName("strMeasure1")
        val strMeasure1: String?, // 50g
        @SerializedName("strMeasure10")
        val strMeasure10: String?, // 3
        @SerializedName("strMeasure11")
        val strMeasure11: String?, // 3 tblsp chopped
        @SerializedName("strMeasure12")
        val strMeasure12: String?, // 1 chopped
        @SerializedName("strMeasure13")
        val strMeasure13: String?,
        @SerializedName("strMeasure14")
        val strMeasure14: String?,
        @SerializedName("strMeasure15")
        val strMeasure15: String?,
        @SerializedName("strMeasure16")
        val strMeasure16: String?,
        @SerializedName("strMeasure17")
        val strMeasure17: String?,
        @SerializedName("strMeasure18")
        val strMeasure18: String?,
        @SerializedName("strMeasure19")
        val strMeasure19: String?,
        @SerializedName("strMeasure2")
        val strMeasure2: String?, // 1 chopped
        @SerializedName("strMeasure20")
        val strMeasure20: String?,
        @SerializedName("strMeasure3")
        val strMeasure3: String?, // 3 Pods
        @SerializedName("strMeasure4")
        val strMeasure4: String?, // 1/4 tsp
        @SerializedName("strMeasure5")
        val strMeasure5: String?, // 1 small
        @SerializedName("strMeasure6")
        val strMeasure6: String?, // Sprigs of fresh
        @SerializedName("strMeasure7")
        val strMeasure7: String?, // 450g
        @SerializedName("strMeasure8")
        val strMeasure8: String?, // 1 Litre
        @SerializedName("strMeasure9")
        val strMeasure9: String?, // 750g
        @SerializedName("strSource")
        val strSource: String?, // https://www.bbcgoodfood.com/recipes/2256/smoked-haddock-kedgeree
        @SerializedName("strTags")
        val strTags: String?, // Brunch,Fish,Fusion
        @SerializedName("strYoutube")
        val strYoutube: String? // https://www.youtube.com/watch?v=QqdzDCWS4gQ
    )
}