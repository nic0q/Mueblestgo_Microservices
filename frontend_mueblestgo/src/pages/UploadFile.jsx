import axios from 'axios';
import React, { useState } from 'react';

export default function UploadFile() {
  const [file, setState] = useState(null);
  const uploadfile = e => {
    console.log(e)
    setState(e.target.files[0]);
  }
  const upload = async(e) => {
    e.preventDefault()
    const url = '/timestamps';
    const formData = new FormData();
    formData.append('file', file);
    const config = {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    };
    axios.post(url, formData, config).then((response) => {
      console.log(response.data);
    });
  };
  return (
    <div>
      <h5>Selecciona un .txt de nombre DATA.txt</h5>
      <input type="file" className="form-control" name="file" onChange={uploadfile}/>
      <button className='btn btn-primary' onClick={upload}>Subir Archivo</button>
    </div>
  );
}
