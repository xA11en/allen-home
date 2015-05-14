//=============================================================================
// Copyright 2006-2013 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================
package com.tingyun.auto.reporter;

import java.util.Comparator;

import org.testng.ITestResult;

/**
 * Comparator for sorting TestNG test results alphabetically by method name.
 * @author Daniel Dyer
 */
class TestResultComparator implements Comparator<ITestResult>
{
    @Override
	public int compare(ITestResult result1, ITestResult result2)
    {
/*		return String.valueOf(result2.getStartMillis()).compareTo(
				String.valueOf(result1.getStartMillis()));*/
		int result = 0;
		if(result1.getStartMillis()<result2.getStartMillis()){
			result = -1;
		}else{
			result = 1;
		}
		return result;
    }
}
