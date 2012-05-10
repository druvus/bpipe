/*
 * Copyright (c) 2012 MCRI, authors
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package bpipe

import groovy.lang.Closure

/**
 * Wraps an inner closure but allows a separate 
 * set of variables to be carried and injected to the
 * closure in a thread-local way when it is executed.
 * 
 * @author simon.sadedin@mcri.edu.au
 */
class ParameterizedClosure extends Closure {
    
    public ParameterizedClosure(Map variables, Closure body) {
        super(body.owner);
        this.body = body
        this.extraVariables = variables
    }
    
    Map extraVariables
    
    Closure body

    @Override
    public Object call() {
      body()
    }    
    
    @Override
    public Object call(Object[] args) {
        body.call(args);
    }
    
    @Override
    public Object call(Object arguments) {
        body.call(arguments);
    }

    @Override
    public void setDelegate(Object delegate) {
        body.setDelegate(delegate);
        super.setDelegate(delegate);
    }
}
