import axios from 'axios';
import React, { useState } from 'react';

export default function UploadFile() {
  const [file, setState] = useState(null);
  const uploadfile = e => {
    setState(e);
  }
  const upload = async() => {
    const formData = new FormData();
    for(let i = 0; i < file.length; i++) {
      formData.append('files', file[i]);
    }
    await axios.post('http://localhost:8080/timestamps', formData).then(res => {
      console.log(res);
    });
  };
  return (
    <div>
      <h5>Selecciona un .txt de nombre DATA.txt</h5>
      <input type="file" className="form-control" name="file" onChange={(e)=>uploadfile(e.target.files)}/>
      <button className='btn btn-primary' onClick={upload}>Subir Archivo</button>
    </div>
  );
}
