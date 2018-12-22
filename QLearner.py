"""  		   	  			    		  		  		    	 		 		   		 		  
Template for implementing QLearner  (c) 2015 Tucker Balch  		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
Copyright 2018, Georgia Institute of Technology (Georgia Tech)  		   	  			    		  		  		    	 		 		   		 		  
Atlanta, Georgia 30332  		   	  			    		  		  		    	 		 		   		 		  
All Rights Reserved  		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
Template code for CS 4646/7646  		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
Georgia Tech asserts copyright ownership of this template and all derivative  		   	  			    		  		  		    	 		 		   		 		  
works, including solutions to the projects assigned in this course. Students  		   	  			    		  		  		    	 		 		   		 		  
and other users of this template code are advised not to share it with others  		   	  			    		  		  		    	 		 		   		 		  
or to make it available on publicly viewable websites including repositories  		   	  			    		  		  		    	 		 		   		 		  
such as github and gitlab.  This copyright statement should not be removed  		   	  			    		  		  		    	 		 		   		 		  
or edited.  		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
We do grant permission to share solutions privately with non-students such  		   	  			    		  		  		    	 		 		   		 		  
as potential employers. However, sharing with other current or future  		   	  			    		  		  		    	 		 		   		 		  
students of CS 7646 is prohibited and subject to being investigated as a  		   	  			    		  		  		    	 		 		   		 		  
GT honor code violation.  		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
-----do not edit anything above this line---  		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
Student Name: Tucker Balch (replace with your name)  		   	  			    		  		  		    	 		 		   		 		  
GT User ID: tb34 (replace with your User ID)  		   	  			    		  		  		    	 		 		   		 		  
GT ID: 900897987 (replace with your GT ID)  		   	  			    		  		  		    	 		 		   		 		  
"""  		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
import numpy as np  		   	  			    		  		  		    	 		 		   		 		  
import random as rand  		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
class QLearner(object):

    def author(self):
        return 'xchen493'  
		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
    def __init__(self, \
        num_states = 100, \
        num_actions = 4, \
        alpha = 0.2, \
        gamma = 0.9, \
        rar = 0.5, \
        radr = 0.99, \
        dyna = 0, \
        verbose = False): 	   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
        self.verbose = verbose  		   	  			    		  		  		    	 		 		   		 		  
        self.num_actions = num_actions
        self.num_states = num_states
        self.rar = rar
        self.radr = radr
        self.R = np.zeros((self.num_states,self.num_actions))
        self.Q = -np.ones((self.num_states, self.num_actions))
        # print self.Q
        self.T = np.full((self.num_states,self.num_actions,self.num_states),0.000000001)
        self.T_c = np.full((self.num_states,self.num_actions,self.num_states),0.00000001)
        self.alpha = alpha
        self.gamma = gamma
        self.dyna = dyna
        self.s = 0  		   	  			    		  		  		    	 		 		   		 		  
        self.a = 0		    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
    def querysetstate(self, s):  		   	  			    		  		  		    	 		 		   		 		  
        """  		   	  			    		  		  		    	 		 		   		 		  
        @summary: Update the state without updating the Q-table  		   	  			    		  		  		    	 		 		   		 		  
        @param s: The new state  		   	  			    		  		  		    	 		 		   		 		  
        @returns: The selected action  		   	  			    		  		  		    	 		 		   		 		  
        """  		   	  			    		  		  		    	 		 		   		 		  
        self.s = s
        if (np.random.uniform() >= self.rar):
        	action = np.argmax(self.Q[s,:])
        else:
        	action = rand.randint(0, self.num_actions - 1)
        self.a = action
        self.rar = self.rar * self.radr
	   	  			    		  		  		    	 		 		   		 		  
        if self.verbose: print "s =", s,"a =",action  		   	  			    		  		  		    	 		 		   		 		  
        return action  		   	  			    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
    def query(self,s_prime,r):  		   	  			    		  		  		    	 		 		   		 		  
        """  		   	  			    		  		  		    	 		 		   		 		  
        @summary: Update the Q table and return an action  		   	  			    		  		  		    	 		 		   		 		  
        @param s_prime: The new state  		   	  			    		  		  		    	 		 		   		 		  
        @param r: The ne state  		   	  			    		  		  		    	 		 		   		 		  
        @returns: The selected action  		   	  			    		  		  		    	 		 		   		 		  
        """  		   	  			    		  		  		    	 		 		   		 		  
        self.Q[self.s, self.a] = (1 - self.alpha) * (self.Q[self.s, self.a]) + self.alpha * (r + self.gamma * self.Q[s_prime,np.argmax(self.Q[s_prime,:])]) # update Q

        if self.dyna > 0:

            self.T_c[self.s,self.a,s_prime] = self.T_c[self.s,self.a,s_prime] + 1
            self.T[self.s,self.a,:] = self.T_c[self.s,self.a,:] / np.sum(self.T_c[self.s,self.a,:])           
            self.R[self.s,self.a] = (1 - self.alpha) * self.R[self.s,self.a] + self.alpha * r          

            

        if np.sum(self.T_c) > 3500 and self.dyna > 0:
        	
            for k in range(0, self.dyna):
                s = rand.randint(0, self.num_states-1)
                a = rand.randint(0, self.num_actions-1)

                if np.sum(self.T_c[s,a,:]) < 1:

                    pass

                else:
                    r = self.R[s,a]
                    s_prime2 = np.argmax(np.random.multinomial(1, self.T[s,a,:]))
                    self.Q[s,a] = (1.0 - self.alpha) * self.Q[s,a] + self.alpha * (r + self.gamma * self.Q[s_prime2,np.argmax(self.Q[s_prime2,:])]) # update Q table

        if self.verbose: print "s =", s_prime,"a =",self.a,"r =",r
        self.a = self.querysetstate(s_prime)
        return self.a	   	  	

		    		  		  		    	 		 		   		 		  
  		   	  			    		  		  		    	 		 		   		 		  
if __name__=="__main__":  		   	  			    		  		  		    	 		 		   		 		  
    print "Remember Q from Star Trek? Well, this isn't him"  		   	  			    		  		  		    	 		 		   		 		  
