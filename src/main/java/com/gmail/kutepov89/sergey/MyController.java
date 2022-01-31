package com.gmail.kutepov89.sergey;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/")
public class MyController {

    private Map<Long, byte[]> photos = new HashMap<Long, byte[]>();

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo.isEmpty())
            throw new PhotoErrorException();

        try {
            long id = System.currentTimeMillis();
            photos.put(id, photo.getBytes());

            model.addAttribute("photo_id", id);
            return "result";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping("/delete/{photo_id}")
    public String onDelete(@PathVariable("photo_id") long id) {
        if (photos.remove(id) == null)
            throw new PhotoNotFoundException();
        else
            return "index";
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

    private void getPhotos(Model model) {
        List<Long> photosIDRes = new ArrayList<>();
        for (Map.Entry<Long, byte[]> photo : photos.entrySet()) {
            photosIDRes.add(photo.getKey());
        }
        model.addAttribute("photo_id", photosIDRes);
    }

    @RequestMapping("/get_all_photos")
    public String getAllPhotos(Model model) {
        getPhotos(model);
        return "getAllPhotos";
    }

    @RequestMapping("/many_del_photos")
    public String getManyPhotos(Model model) {
        getPhotos(model);
        return "delMoreFiles";
    }

    @RequestMapping(value = "/del_many_photos", method = RequestMethod.POST)
    public String checkboxDel(@RequestParam("photoId") Long[] ids) {
        if (ids == null) {
            throw new PhotoNotFoundException();
        } else {
            for (Long id : ids) {
                photos.remove(id);
            }
            return "getAllPhotos";
        }
    }

    @RequestMapping("/create_zip")
    public String getViewForZIP(Model model) {
        getPhotos(model);
        return "createZip";
    }

    @RequestMapping(value = "/create_zip_photos", method = RequestMethod.POST)
    public String createZIP(@RequestParam("photoId") Long[] ids) {
        if (ids == null) {
            throw new PhotoNotFoundException();
        } else {
            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("output.zip"))) {
                for (Long id : ids) {
                    ZipEntry entry1 = new ZipEntry(id + ".png");
                    zout.putNextEntry(entry1);
                    byte[] buffer = photos.get(id);
                    zout.write(buffer);
                    zout.closeEntry();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return "index";
    }

}
