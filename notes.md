# InfoChat

## DB Structures

### Chat

- Members: []Member
- PasswordHash: String
- Messages: []Message

### Member

- ID: String
- Nickname: String
- PublicKey: String
- IsAdmin: Bool

### Message

- ID: String
- Timestamp: Int
- Content: Hashmap<String, String>

## Procedure

### Start

- Check for file.dat
- If true
  - Ask for password
- Else
  - Create File
  - Upload File
  - Ask for password

try {
FileInputStream serviceAccount = new FileInputStream("credential.json");
FirebaseOptions options = FirebaseOptions.builder()
.setCredentials(GoogleCredentials.fromStream(serviceAccount))
.build();

    		FirebaseApp.initializeApp(options);
    	} catch (FileNotFoundException fnfe) {
    		fnfe.printStackTrace();
    	} catch (IOException ioe) {
    		ioe.printStackTrace();
    	}

    	System.out.println("lol");
