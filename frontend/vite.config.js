import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react'


export default () => {

    return defineConfig({
        plugins: [react()],

        server: {
          host: '0.0.0.0',
          port: '5173',
          proxy: {
            '/api': "http://back-end:8080/",
          }
        },
        preview: {
          port: '5173',
        }
    });
}