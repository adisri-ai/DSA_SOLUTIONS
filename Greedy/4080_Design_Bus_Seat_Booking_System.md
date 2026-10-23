# Question  
You are asked to design a seat booking system for a bus.

The bus has n rows. Each row has four seats labeled "A", "B", "C", and "D".

Seats "A" and "D" are window seats; seats "B" and "C" are middle seats. In each row, seat "A" is adjacent to seat "B", and seat "D" is adjacent to seat "C".

Booking rules:

Booking a middle seat ("B" or "C") always takes 1 second.
Booking a window seat ("A" or "D") takes 1 second if its adjacent middle seat is not booked, or 3 seconds (an extra delay of 2 seconds) if its adjacent middle seat is already booked.
A seat is identified by a string such as "A1" or "D20" (one letter from A to D followed by its row number).

Implement the BusBooking class:

BusBooking(int n): Initializes the system with n empty rows.
void toggle(string seat): Books seat if it is free, or frees it if it is already booked.
int getTotalTime(): Returns the total time (in seconds) taken to book all currently booked seats, where the seats are booked in the order their toggle calls were made. If no seats are currently booked, return 0.
int getMinTime(): Returns the smallest possible total time (in seconds) to book all currently booked seats, over every possible order of booking them. If no seats are currently booked, return 0.
Note: The time cost of a seat is fixed at the moment it is booked, based on the seats booked at that time. Booking or freeing other seats afterward does not change the cost of a seat that is already booked.  

# Code (in C++)   
```
class BusBooking {
public:
    long long curr;
    vector<vector<int>>seats;
    long long mi;
    BusBooking(int n) {
        mi = 0;
        curr = 0;
        seats = vector<vector<int>>(n+1 , vector<int>(4 , 0));
    }
    
    void toggle(string seat) {
        int col = seat[0] - 'A';
        int row = stoi(seat.substr(1));
        if(seats[row][col] == 0){
            seats[row][col]++;
            mi+=seats[row][col];
            if(col==0 && seats[row][col+1]) seats[row][col]+=2;
            if(col==3 && seats[row][col-1]) seats[row][col]+=2;
            curr+=seats[row][col];
        }
        else{
            curr-=seats[row][col];
            mi--;
            seats[row][col] = 0;
        }
    }
    
    int getTotalTime() {
        return curr;
    }
    
    int getMinTime() {
        return mi;
    }
};

/**
 * Your BusBooking object will be instantiated and called as such:
 * BusBooking* obj = new BusBooking(n);
 * obj->toggle(seat);
 * int param_2 = obj->getTotalTime();
 * int param_3 = obj->getMinTime();
 */
```
