import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function ImageUpload() {
  const navigate = useNavigate();

  const handleImageChange = (event) => {
    const selectedImages = Array.from(event.target.files);
    navigate('/audio-upload', { state: {selectedImages: selectedImages }});
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '100vh' }}>
      <label htmlFor="imageInput" style={{ cursor: 'pointer', marginBottom: 10 }}>
        <img src="https://s3.ap-northeast-2.amazonaws.com/b205.s3test.bucket/image/upload.png" alt="Upload Icon"/>
      </label>
      <input type="file" id="imageInput" accept="image/*" multiple onChange={handleImageChange} style={{ display: 'none' }} />
      <h2 style={{ fontWeight: 'bold', marginTop: '30px', color: '#000000' }}>Upload Your Image</h2>
      {/* <Footer setSelectedTab={setSelectedTab} /> */}
    </div>
  );
}

export default ImageUpload;
