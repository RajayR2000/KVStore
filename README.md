# File-based Key-Value Store(JAVA)

#### How to use?
#### Prerequisites
1.You need to download the json-jar file from https://code.google.com/archive/p/org-json-java/downloads.

###### Step 1:
Clone this repository to any desired location on your system:

`https://github.com/RajayR2000/KVStore.git` 

or download as a zip file and extract.Now ,add the downloaded jar file from prerequisites to the build path.

Follow following steps to add jar file to build path for Eclipse : 

1. Right-click the project folder and click Properties.
2. Click Java Build Path in the left pane.
3. Click the Libraries tab.
4. Click "Add External JARs", select the JAR file that you downloaded, then click OK.
   


###### Step 2:
Create a new class and import the kvstore package(or you can use the EndUser class that I have created in the default package).
Create a KVStore object.

`KVStore obj = new KVStore();`

###### Step 3:
Initialise JSON object with the json value you want.
This initialisation might throw JSONException,so you might have to deal with that.
After creating the JSON object,you can now call the methods that you want(Create,Read,Delete)

```
try {
JSONObject j1 = new JSONObject("{\"name\":\"John\", \"age\":30, \"car\":null }");

obj.create("John" , j1 , 10) // or obj.create("John" , j1);
}
```
__________________

    
### Methods
#### Create
The create function in the KVStore takes either 2 or 3 arguments.

`create(String key , JSONObject value)`

`create(String key , JSONObject value , long TimeToLive)`

If you create include the TimeToLive value while creation,then the created entry will be deleted after TTL seconds.

#### Read
The read function in the KVStore takes either 1 argument.

`read(String key)`

This will return the JSON value stored to the corresponding key.


#### Delete
The delete function in the KVStore takes either 1 argument.

`delete(String key)`

This will delete the key-value pair from the storage.

_____________________

### Time-To-Live

The TTL property can be satisfied with the help of the **schedule()** function of the Timer class.

It takes two arguments.

`schedule(task to be performed , time after which the task is to be performed in milliseconds)`
The task to be performed can be mentioned by over-riding run() of the TimerTask.

For example:  ` schedule(task , 1000)` 

Here the mentioned task is performed after 1 second.

Code 
```
public class ClassName extends TimerTask
{
    @Override
    public void run() {
        //TASK
    }

}
```
___________________________
### Screenshots

Output after you run the EndUser.java file

![Screenshot (6)](https://user-images.githubusercontent.com/28715027/103212927-30bfca00-4932-11eb-9fde-75b913e69f29.png)

