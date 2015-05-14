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

import org.testng.ITestNGMethod;

/**
 * Comparator for sorting TestNG test methods.  Sorts method alphabeticaly
 * (first by fully-qualified class name, then by method name).
 * @author Daniel Dyer
 */
class TestMethodComparator implements Comparator<ITestNGMethod>
{
    public int compare(ITestNGMethod method1,
                       ITestNGMethod method2)
    {
        int compare = method2.getRealClass().getName().compareTo(method1.getRealClass().getName());
        if (compare == 0)
        {
            compare = method2.getMethodName().compareTo(method1.getMethodName());
        }
        return compare;
    }
}
