# MoodLogger API Test Script

## Test API endpoints using curl

### 1. Create a test user
```bash
curl -X POST http://localhost:8080/public/create-user \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "testuser",
    "password": "password123",
    "email": "test@example.com"
  }'
```

### 2. Login and create a journal entry
```bash
# First, get basic auth token (base64 encode username:password)
# testuser:password123 = dGVzdHVzZXI6cGFzc3dvcmQxMjM=

curl -X POST http://localhost:8080/journal \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dGVzdHVzZXI6cGFzc3dvcmQxMjM=" \
  -d '{
    "title": "Test Entry",
    "content": "This is a test journal entry",
    "sentiment": "HAPPY"
  }'
```

### 3. Get all journal entries
```bash
curl -X GET http://localhost:8080/journal \
  -H "Authorization: Basic dGVzdHVzZXI6cGFzc3dvcmQxMjM="
```

### 4. Test CORS
```bash
curl -X OPTIONS http://localhost:8080/journal \
  -H "Origin: http://localhost:3000" \
  -H "Access-Control-Request-Method: GET" \
  -H "Access-Control-Request-Headers: Authorization"
```

## Common Issues and Solutions

### CORS Issues:
- Make sure CORS is properly configured in SpringSecurity.java
- Check browser console for CORS errors

### Authentication Issues:
- Verify Basic Auth header format
- Check username/password combination
- Ensure user exists in database

### ID Format Issues:
- MongoDB ObjectId should be 24-character hex string
- Check if IDs are being passed correctly from frontend to backend

### Date/Time Issues:
- Backend uses LocalDateTime
- Frontend should send ISO date strings or let backend set dates
