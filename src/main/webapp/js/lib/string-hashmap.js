function HashMap(){
	// A map is maintained by 2 arrays.
	bucketState = new Array();	// Bucket state holds the hashcodes of all the buckets.
	bucketArr = new Array();	// Bucket array holds the actual buckets that are again array.

	this.put = function(key, value)
					{
						putInMap(key, value);
					};
	this.get = function(key)
					{ 
						return getFromMap(key);
					};
	this.exists = function (key)
					{
						var position = binarySearch(hashcode(key), bucketState);
						if(position >= 0)
							{
							var bucket = bucketArr[position];
							for(var ctr = 0; ctr < bucket.length; ctr++)
								{
								if(bucket[ctr].keyitem == key)
									{
									return true;
									}
								}
							}
						return false;
					};
	function hashcode(key)
	{
		/* 	Iterate over the string and eet each character.
	 		Multiple ASCII of character by its position.
	 	*/
		var hash = 0;
		for(var ctr = 0; ctr < key.length; ctr++)
			{
			hash += (ctr+1) * key.charCodeAt(ctr);
			}
		return hash;
	}
	
	/*
	 * It adds a bucket for a given hash along with key and value.
	 * If the bucket already exists, it does not create a new bucket, but uses the existing one.	 *  
	 */
	function putInMap(key, value)
	{		
		// Check if bucket already exists.
		var position = binarySearch(hashcode(key), bucketState);
		if(position != -1)
			{
			// Yes, the bucket exists. Add key and value in bucket's array.
			var bucket = bucketArr[position];
			// Bucket is an array. Check if the key already exists in the bucket.
			for(var ctr = 0; ctr < bucket.length; ctr++)
				{
				if(bucket[ctr].keyitem == key)
					{
					// The key already exists in the bucket.
					bucket[ctr].valueitem = value;
					return;
					}
				else
					{
					// The key doesnot exist in the bucket. We simply push our mapentry into the bucket.
					bucket.push({keyitem: key, valueitem: value});
					return;
					}
				}
			}
		else
			{
			// The bucket was not found. We need to insert a new bucket.
			if(bucketArr.length == 0)
				{
				// The bucketArr is empty. This is the first node to be inserted.
				bucket = new Array();
				bucket.push({keyitem: key, valueitem: value, hash: hashcode(key)});
				bucketArr.push(bucket);
				bucketState.push(hashcode(key));
				return;
				}
			
			// We need to find the correct position to insert the bucket.
			var insertionPoint = findInsertionPosition(hashcode(key), bucketState);
			if(insertionPoint >= 0)
				{
				// We have a valid insertion point and we need to push all other items 1 step up.
				for(var ctr = bucketState.length; ctr >= insertionPoint ; ctr--)
					{
					bucketState[ctr] = bucketState[ctr-1];
					bucketArr[ctr] = bucketArr[ctr-1];
					}
				
				// Insert our bucket here.
				bucket = new Array();
				bucket.push({keyitem: key, valueitem: value, hash: hashcode(key)});
				bucketArr[insertionPoint] = bucket;
				bucketState[insertionPoint] = hashcode(key);
				return;
				}
			}
	}
	
	function getFromMap(key)
	{
		var position = binarySearch(hashcode(key), bucketState);
		if(position < 0)
			{
			// The bucket does not exist.
			return null;
			}
		
		for(var ctr = 0; ctr < bucketArr[position].length; ctr++)
			{
			if(bucketArr[position][ctr].keyitem == key)
				{
				return bucketArr[position][ctr].valueitem;
				}
			}
		// The key wasn't found even in its bucket.
		return null;
	}
	
	/*
	 * Finds the index at which the number should be inserted in the given array.
	 * Returns -1 if the number already exists.
	 * Else returns the position at which the number is to be inserted.
	 * 
	 * Returns -2 when it is not able to find a place. Such a situation must never occur.
	 */
	function findInsertionPosition(num, arr)
	{
		var len = arr.length;
		if(len == 0 || len == 1)
		{
			return len;
		}
		
		var lo = 0;			// The lowest search bound in binary search.
		var top = len-1;	// The highest search bound in binary search.
		
		var pointer = lo;	// Just the starting to enter to loop. Will be changed once it enters the loop.
		while(lo <= top)
			{
			// Adjust pointer to middle of lo and top.
			pointer = Math.floor((lo + top) / 2);
			
			var item = arr[pointer];
			if(item == num)
				{
				// The item has been found. This is so bad.
				return -1;
				}
			
			if(lo == top)
				{
				if(item < num)
					{
					return pointer + 1;
					}
				else if( item > num)
					{
					return pointer;
					}
				else
					{
					// A situation has come that shouldn't have been there.
					}				
				}
			
			if(item < num)
				{
				if(lo == pointer)
					{
					lo++;
					}
				else
					{
					lo = pointer;
					}				
				continue;
				}
			if(item > num)
				{
				if(top == pointer)
					{
					top--;
					}
				else
					{
					top = pointer;
					}				
				continue;
				}
			}
		// lo went greater than top. So the item is greater than lo but less than top. So not found.
		// It should take top value's position.
		return top;
	}
	
	/*
	 * This code performs a binary search for the given number in the given array.
	 * Note: The array must be sorted. Otherwise the result is not guaranteed.
	 */
	function binarySearch(num, arr)
	{
		var len = arr.length;
		
		if(len == 0)
		{
			return -1;
		}
		
		var lo = 0;			// The lowest search bound in binary search.
		var top = len-1;	// The highest search bound in binary search.
		
		var pointer = lo;	// Just the starting to enter to loop. Will be changed once it enters the loop.
		while(lo <= top)
			{
			// Adjust pointer to middle of lo and top.
			pointer = Math.floor((lo + top) / 2);
			
			var item = arr[pointer];
			if(item == num)
				{
				return [pointer];
				}
			
			if(lo == top && arr[pointer] != num)
				{
				return -1;
				}
			
			if(item < num)
				{
				if(lo == pointer)
					{
					lo++;
					}
				else
					{
					lo = pointer;
					}				
				continue;
				}
			if(item > num)
				{
				if(top == pointer)
					{
					top--;
					}
				else
					{
					top = pointer;
					}				
				continue;
				}
			
			// Else the number should be here according to sorting order but isnt here. 
			return -1;
			}
		// lo went greater than top. So the item is greater than lo but less than top. So not found.
		return -1;
	}	
};